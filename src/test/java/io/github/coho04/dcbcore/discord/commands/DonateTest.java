package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class DonateTest {

    private Donate donate;

    @Mock
    private DCBot dcBotMock;

    @Mock
    private SlashCommandInteractionEvent eventMock;

    @Mock
    private SlashCommandInteraction interactionMock;

    @Mock
    private ReplyCallbackAction replyActionMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        donate = new Donate();
    }

    @Test
    public void shouldRunSlashCommandSuccessfully() {
        // Mock the behavior of getInteraction() method of SlashCommandInteractionEvent
        doReturn(interactionMock).when(eventMock).getInteraction();

        // Mock the behavior of reply() method of SlashCommandInteraction
        doReturn(replyActionMock).when(interactionMock).reply(anyString());

        // Mock the behavior of addComponents() method of ReplyCallbackAction
        doReturn(replyActionMock).when(replyActionMock).addComponents(any(ActionRow.class));
        doNothing().when(replyActionMock).queue();

        donate.runSlashCommand(eventMock, dcBotMock);

        // Verify that getInteraction() was called once
        verify(eventMock, times(1)).getInteraction();

        // Verify interactions with replyActionMock
        verify(replyActionMock, times(1)).addComponents(any(ActionRow.class));
        verify(replyActionMock, times(1)).queue();
    }

    @Test
    public void shouldNotRunSlashCommandWhenEventIsNull() {
        donate.runSlashCommand(null, dcBotMock);

        verify(dcBotMock, never()).getDiscord();
    }

    @Test
    public void shouldNotRunSlashCommandWhenBotIsNull() {
        donate.runSlashCommand(eventMock, null);

        verify(eventMock, never()).getInteraction();
    }
}