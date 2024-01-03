package de.goldendeveloper.dcbcore;

import de.goldendeveloper.dcbcore.discord.Discord;
import de.goldendeveloper.dcbcore.errors.SentryHandler;
import de.goldendeveloper.dcbcore.interfaces.CommandInterface;
import io.sentry.ITransaction;
import io.sentry.Sentry;
import io.sentry.SpanStatus;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DCBot {

    private final LinkedList<ListenerAdapter> events = new LinkedList<>();
    private final LinkedList<CommandInterface> commandDataList = new LinkedList<>();
    private final LinkedList<CommandInterface> removedCommandDataList = new LinkedList<>();
    private final LinkedList<GatewayIntent> gatewayIntentList = new LinkedList<>();
    private Discord discord;
    private final Config config;
    private ClientToServer clientToServer;
    private boolean restart = false;
    private boolean deployment = true;
    private final String[] args;

    public DCBot(String[] args, boolean withServerCommunicator, LinkedList<ListenerAdapter> events, LinkedList<CommandInterface> commandDataList, LinkedList<GatewayIntent> gatewayIntentList, LinkedList<CommandInterface> removedCommandDataList) {
        if (args.length >= 1 && args[0].equalsIgnoreCase("restart")) {
            restart = true;
        }
        this.args = args;
        String device = System.getProperty("os.name").split(" ")[0];
        if (device.equalsIgnoreCase("windows") || device.equalsIgnoreCase("Mac")) {
            deployment = false;
        }
        if (events != null)
            this.events.addAll(events);
        if (commandDataList != null)
            this.commandDataList.addAll(commandDataList);
        if (gatewayIntentList != null)
            this.gatewayIntentList.addAll(gatewayIntentList);
        if (removedCommandDataList != null)
            this.removedCommandDataList.addAll(removedCommandDataList);
        this.config = new Config();
        new SentryHandler(this.config.getSentryDNS(), this);
        ITransaction transaction = Sentry.startTransaction("Application()", "task");
        try {
            discord = new Discord(config.getDiscordToken(), this);
            if (getDeployment() && withServerCommunicator) {
                clientToServer = new ClientToServer(new URI("ws://" + config.getServerHostname() + ":" + config.getServerPort()),
                        config.getProjektName(),
                        discord.getBot().getSelfUser().getAvatarUrl(),
                        discord.getBot().getInviteUrl(Permission.ADMINISTRATOR),
                        getGuildIDsFromGuilds(discord.getBot()),
                        getCommandNameFromCommands(discord.getBot().retrieveCommands().complete()));
                clientToServer.connect();
            }
        } catch (Exception e) {
            transaction.setThrowable(e);
            transaction.setStatus(SpanStatus.INTERNAL_ERROR);
            Sentry.captureException(e);
            System.out.println(e.getMessage());
        } finally {
            transaction.finish();
        }
    }

    @SuppressWarnings("unused")
    public void setDeployment(Boolean deployment) {
        this.deployment = deployment;
    }

    public void setDiscord(Discord discord) {
        this.discord = discord;
    }

    @SuppressWarnings("unused")
    public void setRestart(Boolean restart) {
        this.restart = restart;
    }

    @SuppressWarnings("unused")
    public void setClientToServer(ClientToServer clientToServer) {
        this.clientToServer = clientToServer;
    }

    @SuppressWarnings("unused")
    public String[] getArgs() {
        return args;
    }

    public LinkedList<CommandInterface> getCommandDataList() {
        return commandDataList;
    }

    public LinkedList<CommandInterface> getRemovedCommandDataList() {
        return removedCommandDataList;
    }

    public Discord getDiscord() {
        return discord;
    }

    public Config getConfig() {
        return config;
    }

    public Boolean getRestart() {
        return restart;
    }

    public Boolean getDeployment() {
        return deployment;
    }

    public LinkedList<ListenerAdapter> getEvents() {
        return events;
    }

    public ClientToServer getClientToServer() {
        return clientToServer;
    }

    public LinkedList<GatewayIntent> getGatewayIntentList() {
        return gatewayIntentList;
    }

    private List<String> getGuildIDsFromGuilds(JDA jda) {
        List<String> ids = new ArrayList<>();
        jda.getGuilds().forEach(guild -> ids.add(guild.getId()));
        return ids;
    }

    private List<String> getCommandNameFromCommands(List<Command> commands) {
        List<String> commandNames = new ArrayList<>();
        commands.forEach(command -> commandNames.add(command.getName()));
        return commandNames;
    }
}
