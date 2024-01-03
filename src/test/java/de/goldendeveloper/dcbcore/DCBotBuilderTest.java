package de.goldendeveloper.dcbcore;

import static org.junit.jupiter.api.Assertions.*;

import de.goldendeveloper.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DCBotBuilderTest {

    @BeforeEach
    public void setup() {
        Config.path = "src/test/resources/";
    }

    @Test
    void shouldRegisterGatewayIntents() {
        DCBotBuilder dcBotBuilder = new DCBotBuilder(new String[0]);
        dcBotBuilder.registerGatewayIntents(GatewayIntent.GUILD_MESSAGE_TYPING);
        DCBot dcBot = dcBotBuilder.build();
        assertTrue(dcBot.getGatewayIntentList().contains(GatewayIntent.GUILD_MESSAGE_TYPING));
    }

    @Test
    void shouldNotRegisterNullGatewayIntents() {
        DCBotBuilder dcBotBuilder = new DCBotBuilder(new String[0]);
        dcBotBuilder.registerGatewayIntents(null);
        DCBot dcBot = dcBotBuilder.build();
        assertFalse(dcBot.getGatewayIntentList().contains(null));
    }

    @Test
    void shouldRegisterCommands() {
        CommandInterface mockedCommand = Mockito.mock(CommandInterface.class);
        DCBotBuilder dcBotBuilder = new DCBotBuilder(new String[0]);
        dcBotBuilder.registerCommands(mockedCommand);
        DCBot dcBot = dcBotBuilder.build();
        assertTrue(dcBot.getCommandDataList().contains(mockedCommand));
    }

    @Test
    void shouldNotRegisterNullCommands() {
        DCBotBuilder dcBotBuilder = new DCBotBuilder(new String[0]);
        dcBotBuilder.registerCommands(null);
        DCBot dcBot = dcBotBuilder.build();
        assertFalse(dcBot.getCommandDataList().contains(null));
    }

    @Test
    void shouldRemoveCommands() {
        CommandInterface mockedCommand = Mockito.mock(CommandInterface.class);
        DCBotBuilder dcBotBuilder = new DCBotBuilder(new String[0]);
        dcBotBuilder.registerCommands(mockedCommand);
        dcBotBuilder.removeCommands(mockedCommand);
        DCBot dcBot = dcBotBuilder.build();
        assertTrue(dcBot.getRemovedCommandDataList().contains(mockedCommand));
    }

    @Test
    void shouldNotRemoveNullCommands() {
        DCBotBuilder dcBotBuilder = new DCBotBuilder(new String[0]);
        dcBotBuilder.removeCommands(null);
        DCBot dcBot = dcBotBuilder.build();
        assertFalse(dcBot.getRemovedCommandDataList().contains(null));
    }

    @Test
    void shouldRegisterEvents() {
        ListenerAdapter mockedListenerAdapter = Mockito.mock(ListenerAdapter.class);
        DCBotBuilder dcBotBuilder = new DCBotBuilder(new String[0]);
        dcBotBuilder.registerEvents(mockedListenerAdapter);
        DCBot dcBot = dcBotBuilder.build();
        assertTrue(dcBot.getEvents().contains(mockedListenerAdapter));
    }

    @Test
    void shouldNotRegisterNullEvents() {
        DCBotBuilder dcBotBuilder = new DCBotBuilder(new String[0]);
        dcBotBuilder.registerEvents(null);
        DCBot dcBot = dcBotBuilder.build();
        assertFalse(dcBot.getEvents().contains(null));
    }
}