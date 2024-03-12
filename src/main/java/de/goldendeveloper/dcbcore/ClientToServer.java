package de.goldendeveloper.dcbcore;

import de.goldendeveloper.dcbcore.discord.commands.Restart;
import de.goldendeveloper.dcbcore.enums.CommunicationStatus;
import io.sentry.Sentry;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;
import java.util.List;

public class ClientToServer extends WebSocketClient {

    private final String botName;
    private final String botImage;
    private final String botInvite;
    private final List<String> guildIds;
    private final List<String> commandNames;
    private final DCBot dcBot;

    public ClientToServer(URI serverURI, String botName, String botImage, String botInvite, List<String> guildIds, List<String> commandNames, DCBot dcBot) {
        super(serverURI);
        this.botName = botName;
        this.botImage = botImage;
        this.botInvite = botInvite;
        this.guildIds = guildIds;
        this.commandNames = commandNames;
        this.dcBot = dcBot;
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
        System.out.println("Verbindung geöffnet");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", botName);
        jsonObject.put("type", CommunicationStatus.START);
        jsonObject.put("image", botImage);
        jsonObject.put("invite", botInvite);
        jsonObject.put("server", guildIds);
        jsonObject.put("commands", commandNames);
        send(jsonObject.toString());
    }

    @Override
    public void onMessage(String message) {
        JSONObject jsonObject = new JSONObject(message);
        if (jsonObject.has("type")) {
            String type = jsonObject.getString("type");
            if (type.equalsIgnoreCase("BOT_SYSTEM_RESTART")) {
                System.out.println("Server restarts the bot");
                Restart.restartBot(dcBot.getDiscord().getBot(), dcBot);
            } else if (type.equalsIgnoreCase("BOT_SYSTEM_SHUTDOWN")) {
                System.out.println("Server shutdown the bot");
                dcBot.getDiscord().getBot().shutdown();
            } else if (type.equalsIgnoreCase("SERVER_SYSTEM_SHUTDOWN")) {
                System.out.println("Server shutdown notice");
                this.closeConnection(0, "Server shutdown notice");
            }
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Verbindung geschlossen, weil der " + (remote ? "Server" : "Client") + " die Verbindung geschlossen hat.");
        reconnect(30000);
    }

    @Override
    public void onError(Exception ex) {
        System.out.println(ex.getMessage());
        Sentry.captureException(ex);
    }

    public void updateServer(String guild, DCBot dcBot, CommunicationStatus action) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", dcBot.getConfig().getProjektName());
        jsonObject.put("type", action);
        jsonObject.put("server", dcBot.getDiscord().getBot().getGuilds().size());
        jsonObject.put("guild", guild);
        send(jsonObject.toString());
    }

    public void reconnect(long durationInMillis) {
        new Thread(() -> {
            try {
                long durationInSeconds = durationInMillis / 1000;
                for (long i = durationInSeconds; i > 0; i--) {
                    System.out.println("Versuche, die Verbindung in " + i + " Sekunden erneut herzustellen...");
                    Thread.sleep(1000);
                }
                this.close();
                this.reconnect();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                Sentry.captureException(e);
                System.out.println(e.getMessage());
            }
        }).start();
    }
}