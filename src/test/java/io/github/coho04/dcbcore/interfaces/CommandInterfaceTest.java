package io.github.coho04.dcbcore.interfaces;

import io.github.coho04.dcbcore.DCBot;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommandInterfaceTest {

    @Mock
    private SlashCommandInteractionEvent mockSlashEvent;

    @Mock
    private CommandAutoCompleteInteractionEvent mockAutoCompleteEvent;

    @Mock
    private DCBot mockDCBot;

    @Mock
    private Member mockMember;

    @Mock
    private Role mockRole;

    private CommandInterface commandInterface;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        commandInterface = new TestCommand();
    }

    @Test
    public void testCommandData() {
        CommandData commandData = commandInterface.commandData();
        assertNotNull(commandData);
        assertEquals("test", commandData.getName());
    }


    @Test
    public void testRunSlashCommand() {
        commandInterface.runSlashCommand(mockSlashEvent, mockDCBot);
        verify(mockDCBot, times(1)).getDiscord(); // Example verification
    }


    @Test
    public void testRunCommandAutoComplete() {
        commandInterface.runCommandAutoComplete(mockAutoCompleteEvent, mockDCBot);
    }

    @Test
    public void testHasRole() {
        when(mockMember.getRoles()).thenReturn(Collections.singletonList(mockRole));
        when(mockRole.getName()).thenReturn("TestRole");

        boolean hasRole = commandInterface.hasRole(mockRole, mockMember);
        assertTrue(hasRole);
    }

    // Test implementation of CommandInterface for testing purposes
    private static class TestCommand implements CommandInterface {
        @Override
        public CommandData commandData() {
            return Commands.slash("test", "Test command");
        }

        @Override
        public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
            dcBot.getDiscord();
        }
    }
}
