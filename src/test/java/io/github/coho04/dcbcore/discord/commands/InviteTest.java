package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InviteTest {

    private Invite invite;

    @Mock
    private DCBot dcBotMock;

    @Mock
    private SlashCommandInteractionEvent eventMock;

    @Mock
    private SlashCommandInteraction interactionMock;

    @Mock
    private ReplyCallbackAction replyActionMock;

    @Mock
    private net.dv8tion.jda.api.JDA jdaMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        invite = new Invite();
    }

    @Test
    public void shouldRunSlashCommandSuccessfully() {
        // Mock the behavior of getJDA() method of SlashCommandInteractionEvent
        doReturn(jdaMock).when(eventMock).getJDA();

        // Mock the behavior of setRequiredScopes() and getInviteUrl() methods of JDA
        doReturn(jdaMock).when(jdaMock).setRequiredScopes(anyString());
        when(jdaMock.getInviteUrl(Permission.ADMINISTRATOR)).thenReturn("https://discord.com/invite/example");

        // Mock the behavior of getInteraction() method of SlashCommandInteractionEvent
        doReturn(interactionMock).when(eventMock).getInteraction();

        // Mock the behavior of reply() method of SlashCommandInteraction
        doReturn(replyActionMock).when(interactionMock).reply(anyString());

        // Mock the behavior of addActionRow() method of ReplyCallbackAction
        doReturn(replyActionMock).when(replyActionMock).addActionRow(any(Button.class));
        doNothing().when(replyActionMock).queue();

        invite.runSlashCommand(eventMock, dcBotMock);

        // Verify that getInteraction() was called once
        verify(eventMock, times(1)).getInteraction();

        // Verify interactions with replyActionMock
        verify(replyActionMock, times(1)).addActionRow(any(Button.class));
        verify(replyActionMock, times(1)).queue();
    }

    @Test
    public void shouldNotRunSlashCommandWhenEventIsNull() {
        invite.runSlashCommand(null, dcBotMock);

        verify(dcBotMock, never()).getDiscord();
    }
}