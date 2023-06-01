package de.goldendeveloper.dcbcore;

import io.sentry.Sentry;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.Command;
import org.json.JSONObject;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ServerCommunicator {

    public enum action {
        ADD,
        REMOVE,
        START
    }

    private final String HOSTNAME;
    private final int PORT;

    public ServerCommunicator(String HOSTNAME, int PORT) {
        this.HOSTNAME = HOSTNAME;
        this.PORT = PORT;
    }

    public void startBot(JDA bot) {
        JSONObject data = new JSONObject();
        data.put("name", bot.getSelfUser().getName());
        data.put("type", action.START);
        data.put("server", getGuildIDsFromGuilds(bot));
        data.put("image", bot.getSelfUser().getAvatarUrl());
        data.put("invite", bot.getInviteUrl(Permission.ADMINISTRATOR));
        data.put("commands", getCommandNameFromCommands(bot.retrieveCommands().complete()));
        sendDataToServer(data);
    }

    public void addServer(String guild, DCBot bot) {
        sendDataToServer(getData(guild, bot, action.ADD));
    }

    public void removeServer(String guild, DCBot dcBot) {
        sendDataToServer(getData(guild, dcBot, action.REMOVE));
    }

    public JSONObject getData(String guild, DCBot bot, action action) {
        JSONObject data = new JSONObject();
        data.put("name", bot.getConfig().getProjektName());
        data.put("type", action);
        data.put("server", bot.getDiscord().getBot().getGuilds().size());
        data.put("guild", guild);
        return data;
    }

    private void sendDataToServer(JSONObject jsonObject) {
        Socket socket = null;
        try {
            socket = new Socket(this.HOSTNAME, this.PORT);
            OutputStream raus = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(raus, StandardCharsets.UTF_8);
            osw.write(jsonObject.toString());
            osw.flush();
            osw.close();
        } catch (Exception e) {
            Sentry.captureException(e);
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    Sentry.captureException(e);
                    e.printStackTrace();
                }
            }
        }
    }

    public List<String> getGuildIDsFromGuilds(JDA jda) {
        List<String> ids = new ArrayList<>();
        jda.getGuilds().forEach(guild -> ids.add(guild.getId()));
        return ids;
    }

    public List<String> getCommandNameFromCommands(List<Command> commands) {
        List<String> commandNames = new ArrayList<>();
        commands.forEach(command -> commandNames.add(command.getName()));
        return commandNames;
    }
}