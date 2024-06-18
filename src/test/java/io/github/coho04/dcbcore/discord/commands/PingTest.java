package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PingTest {

    private Ping ping;

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
        ping = new Ping();
    }

/*    @Test
    public void shouldRunSlashCommandSuccessfully() {
        // Mock the behavior of getInteraction() method of SlashCommandInteractionEvent
        when(eventMock.getInteraction()).thenReturn(interactionMock);

        // Mock the behavior of reply() method of SlashCommandInteraction
        when(interactionMock.reply(anyString())).thenReturn(replyActionMock);

        // Mock the behavior of queue() method of ReplyCallbackAction
        doNothing().when(replyActionMock).queue(any());

        ping.runSlashCommand(eventMock, dcBotMock);

        // Verify that getInteraction() was called once
        verify(eventMock, times(1)).getInteraction();

        // Verify interactions with replyActionMock
        verify(replyActionMock, times(1)).queue(any());
    }*/
}
