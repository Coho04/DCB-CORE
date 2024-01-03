package de.goldendeveloper.dcbcore.discord;

import de.goldendeveloper.dcbcore.DCBot;
import de.goldendeveloper.dcbcore.discord.commands.*;
import de.goldendeveloper.dcbcore.interfaces.CommandInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class DiscordTest {

    private DCBot dcBot;
    private Discord discord;

    @BeforeEach
    public void setup() {
        dcBot = Mockito.mock(DCBot.class);
        discord = new Discord("testToken", dcBot);
    }

    @Test
    public void shouldAddDefaultCommandsOnRegister() {
        LinkedList<CommandInterface> expectedCommands = new LinkedList<>();
        Collections.addAll(expectedCommands, new BotStats(), new BotOwner(), new Donate(), new Help(), new Invite(), new Join(), new Ping(), new Restart(), new Shutdown());

        discord.registerDefaultCommand();

        LinkedList<CommandInterface> actualCommands = discord.getCommands();

        assertTrue(actualCommands.containsAll(expectedCommands), "All default commands should be registered");
    }

    @Test
    public void shouldNotAddDefaultCommandsIfTheyAreInRemovedCommandsList() {
        LinkedList<CommandInterface> removedCommands = new LinkedList<>();
        Collections.addAll(removedCommands, new BotStats(), new BotOwner());

        when(dcBot.getRemovedCommandDataList()).thenReturn(removedCommands);

        discord.registerDefaultCommand();

        LinkedList<CommandInterface> actualCommands = discord.getCommands();

        assertTrue(!actualCommands.containsAll(removedCommands), "Removed commands should not be registered");
    }
}
