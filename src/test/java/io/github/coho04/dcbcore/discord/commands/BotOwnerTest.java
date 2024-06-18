package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class BotOwnerTest {

    @Mock
    private SlashCommandInteractionEvent eventMock;

    @Mock
    private SlashCommandInteraction interactionMock;

    @Mock
    private DCBot dcBotMock;

    @Mock
    private ReplyCallbackAction replyActionMock;

    private BotOwner botOwner;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        botOwner = new BotOwner();
    }

/*    @Test
    public void shouldRunSlashCommandSuccessfully() {
        // Mock the behavior of getInteraction() method of SlashCommandInteractionEvent
        doReturn(interactionMock).when(eventMock).getInteraction();

        // Mock the behavior of reply() method of SlashCommandInteraction
        doReturn(replyActionMock).when(interactionMock).reply(anyString());

        // Mock the behavior of addActionRow() method of ReplyCallbackAction
        doReturn(replyActionMock).when(replyActionMock).addActionRow(any(Button.class));
        doNothing().when(replyActionMock).queue();

        botOwner.runSlashCommand(eventMock, dcBotMock);

        // Verify that getInteraction() was called once
        verify(eventMock, times(1)).getInteraction();

        // Verify interactions with replyActionMock
        verify(replyActionMock, times(1)).addActionRow(any(Button.class));
        verify(replyActionMock, times(1)).queue();
    }*/

/*    @Test
    public void shouldNotRunSlashCommandWhenEventIsNull() {
        botOwner.runSlashCommand(null, dcBotMock);

        verify(eventMock, never()).getInteraction();
    }*/

//    @Test
//    public void shouldNotRunSlashCommandWhenBotIsNull() {
//        botOwner.runSlashCommand(eventMock, null);
//
//        verify(eventMock, never()).getInteraction();
//    }
}
