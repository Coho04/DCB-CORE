package de.goldendeveloper.dcbcore;

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

    public ClientToServer(URI serverURI, String botName, String botImage, String botInvite, List<String> guildIds, List<String> commandNames) {
        super(serverURI);
        this.botName = botName;
        this.botImage = botImage;
        this.botInvite = botInvite;
        this.guildIds = guildIds;
        this.commandNames = commandNames;
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
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
        System.out.println("Nachricht vom Server: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        if (remote) {
            System.out.println("Verbindung geschlossen, weil der Server die Verbindung geschlossen hat.");
        } else {
            System.out.println("Verbindung geschlossen, weil der Client die Verbindung geschlossen hat.");
        }

        new Thread(() -> {
            try {
                System.out.println("Reconnect to Server");
                Thread.sleep(5000);
                ClientToServer.this.reconnectBlocking();
            } catch (InterruptedException | IllegalStateException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void onError(Exception ex) {
        Sentry.captureException(ex);
        System.out.println(ex.getMessage());
    }

    public void updateServer(String guild, DCBot dcBot, CommunicationStatus action) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", dcBot.getConfig().getProjektName());
        jsonObject.put("type", action);
        jsonObject.put("server", dcBot.getDiscord().getBot().getGuilds().size());
        jsonObject.put("guild", guild);
        send(jsonObject.toString());
    }
}