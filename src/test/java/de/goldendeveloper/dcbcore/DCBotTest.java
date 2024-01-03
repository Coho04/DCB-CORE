package de.goldendeveloper.dcbcore;

import de.goldendeveloper.dcbcore.discord.Discord;
import de.goldendeveloper.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class DCBotTest {

    private DCBot bot;
    private String[] args = {"restart"};
    private LinkedList<ListenerAdapter> events;
    private LinkedList<CommandInterface> commandDataList;
    private LinkedList<GatewayIntent> gatewayIntentList;
    private LinkedList<CommandInterface> removedCommandDataList;

    @BeforeEach
    public void setup() {
        events = new LinkedList<>();
        commandDataList = new LinkedList<>();
        gatewayIntentList = new LinkedList<>();
        removedCommandDataList = new LinkedList<>();
        bot = new DCBot(args, true, events, commandDataList, gatewayIntentList, removedCommandDataList);
    }

    @Test
    public void shouldReturnArgs() {
        assertArrayEquals(args, bot.getArgs());
    }

    @Test
    public void shouldReturnCommandDataList() {
        assertEquals(commandDataList, bot.getCommandDataList());
    }

    @Test
    public void shouldReturnRemovedCommandDataList() {
        assertEquals(removedCommandDataList, bot.getRemovedCommandDataList());
    }

    @Test
    public void shouldReturnEvents() {
        assertEquals(events, bot.getEvents());
    }

    @Test
    public void shouldReturnGatewayIntentList() {
        assertEquals(gatewayIntentList, bot.getGatewayIntentList());
    }

    @Test
    public void shouldReturnRestartStatus() {
        assertTrue(bot.getRestart());
    }

    @Test
    public void shouldReturnDeploymentStatus() {
        assertFalse(bot.getDeployment());
    }

    @Test
    public void shouldReturnWithServerCommunicatorStatus() {
        assertTrue(bot.getWithServerCommunicator());
    }

    @Test
    public void shouldSetAndReturnDiscord() {
        Discord discord = Mockito.mock(Discord.class);
        bot.setDiscord(discord);
        assertEquals(discord, bot.getDiscord());
    }

    @Test
    public void shouldSetAndReturnServerCommunicator() {
        ServerCommunicator serverCommunicator = Mockito.mock(ServerCommunicator.class);
        bot.setServerCommunicator(serverCommunicator);
        assertEquals(serverCommunicator, bot.getServerCommunicator());
    }

    @Test
    public void shouldSetAndReturnDeploymentStatus() {
        bot.setDeployment(true);
        assertTrue(bot.getDeployment());
    }

    @Test
    public void shouldSetAndReturnRestartStatus() {
        bot.setRestart(false);
        assertFalse(bot.getRestart());
    }
}
