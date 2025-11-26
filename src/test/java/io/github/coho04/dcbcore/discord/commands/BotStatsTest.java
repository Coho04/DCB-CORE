package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.channel.unions.DefaultGuildChannelUnion;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import net.dv8tion.jda.api.requests.restaction.InviteAction;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class BotStatsTest {

    private BotStats botStats;

    @Mock
    private DCBot dcBotMock;

    @Mock
    private SlashCommandInteractionEvent eventMock;

    @Mock
    private SlashCommandInteraction interactionMock;

    @Mock
    private ReplyCallbackAction replyActionMock;

    @Mock
    private JDA jdaMock;

    @Mock
    private Guild guildMock;

    @Mock
    private DefaultGuildChannelUnion defaultChannelMock;

    @Mock
    private SelfUser selfUserMock;

    @Mock
    private InviteAction inviteActionMock;

    @Mock
    private Invite inviteMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        botStats = new BotStats();
    }

    @Test
    public void shouldRunSlashCommandSuccessfully() {
        // Mock the behavior of getJDA() method of SlashCommandInteractionEvent
        doReturn(jdaMock).when(eventMock).getJDA();

        // Mock the behavior of getGuildById() method of JDA
        doReturn(guildMock).when(jdaMock).getGuildById(anyLong());

        // Mock the behavior of getSelfUser() method of JDA
        doReturn(selfUserMock).when(jdaMock).getSelfUser();

        // Mock the behavior of getAvatarUrl() method of SelfUser
        when(selfUserMock.getAvatarUrl()).thenReturn("https://example.com/avatar.png");

        // Mock the behavior of getGuilds() method of JDA
        when(jdaMock.getGuilds()).thenReturn(Collections.singletonList(guildMock));

        // Mock the behavior of getName() method of Guild
        when(guildMock.getName()).thenReturn("Test Guild");

        // Mock the behavior of getDefaultChannel() method of Guild
        doReturn(defaultChannelMock).when(guildMock).getDefaultChannel();

        // Mock the behavior of createInvite() method of DefaultGuildChannelUnion
        doReturn(inviteActionMock).when(defaultChannelMock).createInvite();

        // Mock the behavior of complete() method of InviteAction
        doReturn(inviteMock).when(inviteActionMock).complete();

        // Mock the behavior of getUrl() method of Invite
        when(inviteMock.getUrl()).thenReturn("https://discord.gg/example");

        // Mock the behavior of getInteraction() method of SlashCommandInteractionEvent
        doReturn(interactionMock).when(eventMock).getInteraction();

        // Mock the behavior of replyEmbeds() method of SlashCommandInteraction
        doReturn(replyActionMock).when(interactionMock).replyEmbeds(any(MessageEmbed.class));

        // Mock the behavior of addComponents() method of ReplyCallbackAction
        doReturn(replyActionMock).when(replyActionMock).addComponents(any(ActionRow.class));
        doNothing().when(replyActionMock).queue();

        botStats.runSlashCommand(eventMock, dcBotMock);

        // Verify that getInteraction() was called once
        verify(eventMock, times(1)).getInteraction();

        // Verify interactions with replyActionMock
        verify(replyActionMock, times(1)).addComponents(any(ActionRow.class));
        verify(replyActionMock, times(1)).queue();
    }

    @Test
    public void shouldNotRunSlashCommandWhenEventIsNull() {
        botStats.runSlashCommand(null, dcBotMock);

        verify(dcBotMock, never()).getDiscord();
    }

    @Test
    public void shouldNotRunSlashCommandWhenBotIsNull() {
        botStats.runSlashCommand(eventMock, null);

        verify(eventMock, never()).getInteraction();
    }
}