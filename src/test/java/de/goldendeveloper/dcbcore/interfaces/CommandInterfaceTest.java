package de.goldendeveloper.dcbcore.interfaces;

import de.goldendeveloper.dcbcore.DCBot;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CommandInterfaceTest {

    @Mock
    private Member member;

    @Mock
    private Role role;

    @Mock
    private DCBot dcBot;

    @Mock
    private SlashCommandInteractionEvent slashCommandInteractionEvent;

    @Mock
    private CommandAutoCompleteInteractionEvent commandAutoCompleteInteractionEvent;

    private CommandInterface commandInterface;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        commandInterface = new CommandInterface() {
            @Override
            public CommandData commandData() {
                return null;
            }

            @Override
            public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
            }
        };
    }

    @Test
    public void memberHasRole() {
        when(member.getRoles()).thenReturn(Collections.singletonList(role));
        assertTrue(commandInterface.hasRole(role, member));
    }

    @Test
    public void memberDoesNotHaveRole() {
        when(member.getRoles()).thenReturn(Collections.emptyList());
        assertFalse(commandInterface.hasRole(role, member));
    }

    @Test
    public void runSlashCommand() {
        commandInterface.runSlashCommand(slashCommandInteractionEvent, dcBot);
    }

    @Test
    public void runCommandAutoComplete() {
        commandInterface.runCommandAutoComplete(commandAutoCompleteInteractionEvent, dcBot);
    }
}