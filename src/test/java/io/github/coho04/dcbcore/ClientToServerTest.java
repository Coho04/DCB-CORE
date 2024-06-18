package io.github.coho04.dcbcore;

import io.sentry.Sentry;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import io.github.coho04.dcbcore.discord.Discord;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClientToServerTest {

    private ClientToServer clientToServer;
    private DCBot dcBotMock;
    private ServerHandshake serverHandshakeMock;

    @Mock
    private DCBot mockDCBot;

    @Mock
    private Discord mockDiscord;

    @Mock
    private org.java_websocket.WebSocket mockWebSocket;


    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
        dcBotMock = mock(DCBot.class);
        serverHandshakeMock = mock(ServerHandshake.class);
        URI serverURI = new URI("ws://localhost:8080");
        List<String> guildIds = Arrays.asList("guild1", "guild2");
        List<String> commandNames = Arrays.asList("command1", "command2");
        clientToServer = new ClientToServer(serverURI, "botName", "botImage", "botInvite", guildIds, commandNames, dcBotMock);
    }

    @Test
    public void testOnError() {
        List<String> guildIds = List.of("1234567890");
        List<String> commandNames = Arrays.asList("command1", "command2");

        ClientToServer client = new ClientToServer(URI.create("ws://localhost:8080"), "TestBot", "bot.jpg", "bot_invite_link", guildIds, commandNames, mockDCBot);

        Exception mockException = mock(Exception.class);

        // Mock Sentry class to use for verification
        Sentry sentryMock = mock(Sentry.class);

        // Call onError method with mockException
        client.onError(mockException);

        verify(sentryMock, times(1)).captureException(eq(mockException));
    }

}
