package de.goldendeveloper.dcbcore;

import de.goldendeveloper.dcbcore.discord.Discord;
import de.goldendeveloper.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DCBotTest {
//
//    @Mock
//    private Discord mockedDiscord;
//    @Mock
//    private Config mockedConfig;
//    @Mock
//    private ServerCommunicator mockedServerCommunicator;
//    @Mock
//    private ListenerAdapter mockedListenerAdapter;
//
//    private LinkedList<ListenerAdapter> events;
//    private LinkedList<CommandInterface> commandDataList;
//    private LinkedList<GatewayIntent> gatewayIntentsList;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        events = new LinkedList<>();
//        commandDataList = new LinkedList<>();
//        gatewayIntentsList = new LinkedList<>();
//    }
//
//    @Test
//    public void testApplication() {
//        DCBot dcBot = new DCBot(new String[]{"restart"}, true, events, commandDataList, gatewayIntentsList);
//        Mockito.when(mockedConfig.getDiscordToken()).thenReturn("mockedToken");
//        dcBot.setDiscord(mockedDiscord);
//        dcBot.application();
////        verify(mockedConfig).getDiscordToken();
//    }
//
//    @Test
//    public void testGetArgs() {
//        DCBot dcBot = new DCBot(new String[]{"restart"}, true, events, commandDataList, gatewayIntentsList);
//        String[] actualArgs = dcBot.getArgs();
//        assertEquals(new String[]{"restart"}, actualArgs);
//    }
//
//    @Test
//    public void testGetCommandDataList() {
//        DCBot dcBot = new DCBot(new String[]{"restart"}, true, events, commandDataList, gatewayIntentsList);
//        LinkedList<CommandInterface> actualCommandDataList = dcBot.getCommandDataList();
//        Assertions.assertNotNull(actualCommandDataList);
//    }
//
//    @Test
//    public void testGetDiscord() {
//        DCBot dcBot = new DCBot(new String[]{"restart"}, true, events, commandDataList, gatewayIntentsList);
//        dcBot.setDiscord(mockedDiscord);
//        Discord actualDiscord = dcBot.getDiscord();
//        assertEquals(mockedDiscord, actualDiscord);
//    }
//
//    @Test
//    public void testGetConfig() {
//        DCBot dcBot = new DCBot(new String[]{"restart"}, true, events, commandDataList, gatewayIntentsList);
//        Config actualConfig = dcBot.getConfig();
//        Assertions.assertNotNull(actualConfig);
//    }
//
//    @Test
//    public void testGetRestart() {
//        DCBot dcBot = new DCBot(new String[]{"restart"}, true, events, commandDataList, gatewayIntentsList);
//        dcBot.setRestart(true);
//        Boolean actualRestart = dcBot.getRestart();
//        assertEquals(true, actualRestart);
//    }
//
//    @Test
//    public void testGetDeployment() {
//        DCBot dcBot = new DCBot(new String[]{"restart"}, true, events, commandDataList, gatewayIntentsList);
//        dcBot.setDeployment(true);
//        Boolean actualDeployment = dcBot.getDeployment();
//        assertEquals(true, actualDeployment);
//    }
//
//    @Test
//    public void testGetServerCommunicator() {
//        DCBot dcBot = new DCBot(new String[]{"restart"}, true, events, commandDataList, gatewayIntentsList);
//        dcBot.setServerCommunicator(mockedServerCommunicator);
//        ServerCommunicator actualServerCommunicator = dcBot.getServerCommunicator();
//        assertEquals(mockedServerCommunicator, actualServerCommunicator);
//    }
//
//    @Test
//    public void testGetWithServerCommunicator() {
//        DCBot dcBot = new DCBot(new String[]{"restart"}, true, events, commandDataList, gatewayIntentsList);
//        boolean actualWithServerCommunicator = dcBot.getWithServerCommunicator();
//        assertTrue(actualWithServerCommunicator);
//    }
//
//    @Test
//    public void testGetEvents() {
//        DCBot dcBot = new DCBot(new String[]{"restart"}, true, events, commandDataList, gatewayIntentsList);
//        dcBot.getEvents().add(mockedListenerAdapter);
//        Assertions.assertNotNull(dcBot.getEvents());
//    }
}
