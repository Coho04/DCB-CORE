package io.github.coho04.dcbcore;

import io.github.coho04.dcbcore.discord.Discord;
import io.github.coho04.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.junit.jupiter.api.BeforeEach;

import java.util.LinkedList;

import static org.mockito.Mockito.*;

class DCBotTest {

    private DCBot bot;

    @BeforeEach
    public void setup() {
        Discord discordMock = mock(Discord.class);
        JDA jdaMock = mock(JDA.class);
        when(discordMock.getBot()).thenReturn(jdaMock);

        LinkedList<ListenerAdapter> events = new LinkedList<>();
        LinkedList<CommandInterface> commandDataList = new LinkedList<>();
        LinkedList<GatewayIntent> gatewayIntentList = new LinkedList<>();
        LinkedList<CommandInterface> removedCommandDataList = new LinkedList<>();

        bot = new DCBot(new String[]{}, false, events, commandDataList, gatewayIntentList, removedCommandDataList);
        bot.setDiscord(discordMock);
    }

//    @Test
//    public void shouldReturnCorrectArgs() {
//        String[] args = {"arg1", "arg2"};
//        bot = new DCBot(args, false, null, null, null, null);
//        assertArrayEquals(args, bot.getArgs());
//    }
//
//    @Test
//    public void shouldReturnCorrectDeploymentStatus() {
//        bot.setDeployment(true);
//        assertTrue(bot.getDeployment());
//        bot.setDeployment(false);
//        assertFalse(bot.getDeployment());
//    }
//
//    @Test
//    public void shouldReturnCorrectRestartStatus() {
//        bot.setRestart(true);
//        assertTrue(bot.getRestart());
//        bot.setRestart(false);
//        assertFalse(bot.getRestart());
//    }

//    @Test
//    public void shouldReturnCorrectCommandNames() {
//        LinkedList<Command> commands = new LinkedList<>();
//        Command command1 = mock(Command.class);
//        Command command2 = mock(Command.class);
//        when(command1.getName()).thenReturn("command1");
//        when(command2.getName()).thenReturn("command2");
//        commands.add(command1);
//        commands.add(command2);
//        LinkedList<String> expectedCommandNames = new LinkedList<>();
//        expectedCommandNames.add("command1");
//        expectedCommandNames.add("command2");
//        assertEquals(expectedCommandNames, bot.getCommandNameFromCommands(commands));
//    }
}
