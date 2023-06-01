package de.goldendeveloper.dcbcore;

import static org.junit.jupiter.api.Assertions.*;

import de.goldendeveloper.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.LinkedList;

class DCBotBuilderTest {

//    @Mock
//    private CommandInterface mockedCommand;
//
//    @Mock
//    private ListenerAdapter mockedListenerAdapter;
//
//    private DCBotBuilder dcBotBuilder;
//
//    @BeforeEach
//    void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testBuildWithServerCommunicator() {
//        String[] args = {"arg1", "arg2"};
//        dcBotBuilder = new DCBotBuilder(args, true);
//        dcBotBuilder.registerEvents(mockedListenerAdapter);
//        dcBotBuilder.registerCommands(mockedCommand);
//        DCBot dcBot = dcBotBuilder.build();
//        assertEquals(args, dcBot.getArgs());
//        assertTrue(dcBot.getWithServerCommunicator());
//    }
//
//    @Test
//    void testBuildWithoutServerCommunicator() {
//        String[] args = {"arg1", "arg2"};
//        dcBotBuilder = new DCBotBuilder(args);
//        dcBotBuilder.registerCommands(mockedCommand);
//        dcBotBuilder.registerEvents(mockedListenerAdapter);
//        DCBot dcBot = dcBotBuilder.build();
//        assertEquals(args, dcBot.getArgs());
//        assertFalse(dcBot.getWithServerCommunicator());
//    }
//
//    @Test
//    void testRegisterCommands() {
//        dcBotBuilder = new DCBotBuilder(new String[0]);
//        dcBotBuilder.registerCommands(mockedCommand);
//        dcBotBuilder.registerEvents(mockedListenerAdapter);
//        dcBotBuilder.build();
//    }
//
//    @Test
//    void testRegisterEvents() {
//        dcBotBuilder = new DCBotBuilder(new String[0]);
//        dcBotBuilder.registerEvents(mockedListenerAdapter);
//        LinkedList<ListenerAdapter> events = new LinkedList<>();
//        events.add(mockedListenerAdapter);
//        DCBot dcBot = dcBotBuilder.build();
//        assertEquals(events, dcBot.getEvents());
//    }
//
//    @Test
//    void testRegisterGatewayIntents() {
//        dcBotBuilder = new DCBotBuilder(new String[0]);
//        dcBotBuilder.registerGatewayIntents(GatewayIntent.GUILD_MESSAGE_TYPING);
//        LinkedList<GatewayIntent> gatewayIntents = new LinkedList<>();
//        gatewayIntents.add(GatewayIntent.GUILD_MESSAGE_TYPING);
//        DCBot dcBot = dcBotBuilder.build();
//        assertEquals(gatewayIntents, dcBot.getGatewayIntentList());
//    }
}
