package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.requests.restaction.InviteAction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class JoinTest {

    private Join join;

    @Mock
    private DCBot dcBotMock;

    @Mock
    private SlashCommandInteractionEvent eventMock;

    @Mock
    private SlashCommandInteraction interactionMock;

    @Mock
    private JDA jdaMock;

    @Mock
    private Guild guildMock;

    @Mock
    private TextChannel textChannelMock;

    @Mock
    private InviteAction inviteActionMock;

    @Mock
    private Invite inviteMock;

    @Mock
    private ReplyCallbackAction replyActionMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        join = new Join();
    }

    @Test
    public void shouldRunSlashCommandSuccessfullyWhenGuildNotNull() {
        when(eventMock.getJDA()).thenReturn(jdaMock);
        when(jdaMock.getGuildById(anyLong())).thenReturn(guildMock);
        when(guildMock.getTextChannelById(anyLong())).thenReturn(textChannelMock);
        when(textChannelMock.createInvite()).thenReturn(inviteActionMock);
        when(inviteActionMock.complete()).thenReturn(inviteMock);
        when(inviteMock.getUrl()).thenReturn("https://discord.gg/example");

        when(eventMock.getInteraction()).thenReturn(interactionMock);
        when(interactionMock.reply(anyString())).thenReturn(replyActionMock);
        when(replyActionMock.addActionRow(any(Button.class))).thenReturn(replyActionMock);
        doNothing().when(replyActionMock).queue();

        join.runSlashCommand(eventMock, dcBotMock);

        verify(eventMock, times(1)).getInteraction();
        verify(replyActionMock, times(1)).addActionRow(any(Button.class));
        verify(replyActionMock, times(1)).queue();
    }

    @Test
    public void shouldNotRunSlashCommandWhenGuildIsNull() {
        when(eventMock.getJDA()).thenReturn(jdaMock);
        when(jdaMock.getGuildById(anyLong())).thenReturn(null);

        when(eventMock.getInteraction()).thenReturn(interactionMock);
        when(interactionMock.reply(anyString())).thenReturn(replyActionMock);
        doNothing().when(replyActionMock).queue();

        join.runSlashCommand(eventMock, dcBotMock);

        verify(eventMock, times(1)).getInteraction();
        verify(replyActionMock, times(1)).queue();
    }
}