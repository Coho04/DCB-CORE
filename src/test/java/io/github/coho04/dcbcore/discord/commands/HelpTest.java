package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HelpTest {

    private Help help;

    @Mock
    private DCBot dcBotMock;

    @Mock
    private SlashCommandInteractionEvent eventMock;

    @Mock
    private JDA jdaMock;

    @Mock
    private RestAction<List<Command>> restActionMock;

    @Mock
    private SlashCommandInteraction interactionMock;

    @Mock
    private ReplyCallbackAction replyActionMock;

    @Mock
    private SelfUser selfUserMock;

    @Mock
    private Command commandMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        help = new Help();
    }

    @Test
    public void shouldRunSlashCommandSuccessfully() {
//        // Mock the behavior of getJDA() method of SlashCommandInteractionEvent
//        doReturn(jdaMock).when(eventMock).getJDA();
//
//        // Mock the behavior of retrieveCommands() method of JDA
//        doReturn(restActionMock).when(jdaMock).retrieveCommands();
//
//        // Mock the behavior of complete() method of RestAction
//        List<Command> commands = Collections.singletonList(commandMock);
//        doReturn(commands).when(restActionMock).complete();
//
//        // Mock the behavior of Command methods
//        when(commandMock.getName()).thenReturn("test");
//        when(commandMock.getDescription()).thenReturn("This is a test command");
//        when(commandMock.isNSFW()).thenReturn(false); // Add this mock to override isNSFW method
//
//        // Mock the behavior of getSelfUser() method of JDA
//        doReturn(selfUserMock).when(jdaMock).getSelfUser();
//
//        // Mock the behavior of getAvatarUrl() method of SelfUser
//        when(selfUserMock.getAvatarUrl()).thenReturn("https://example.com/avatar.png");
//
//        // Mock the behavior of getInteraction() method of SlashCommandInteractionEvent
//        doReturn(interactionMock).when(eventMock).getInteraction();
//
//        // Mock the behavior of replyEmbeds() method of SlashCommandInteraction
//        doReturn(replyActionMock).when(interactionMock).replyEmbeds(any(MessageEmbed.class));
//
//        // Mock the behavior of addActionRow() method of ReplyCallbackAction
//        doReturn(replyActionMock).when(replyActionMock).addActionRow(any(Button.class), any(Button.class));
//        doNothing().when(replyActionMock).queue();
//
//        help.runSlashCommand(eventMock, dcBotMock);
//
//        // Verify that getInteraction() was called once
//        verify(eventMock, times(1)).getInteraction();
//
//        // Verify interactions with replyActionMock
//        verify(replyActionMock, times(1)).addActionRow(any(Button.class), any(Button.class));
//        verify(replyActionMock, times(1)).queue();
    }

    @Test
    public void shouldNotRunSlashCommandWhenEventIsNull() {
        help.runSlashCommand(null, dcBotMock);

        verify(dcBotMock, never()).getDiscord();
    }

    @Test
    public void shouldNotRunSlashCommandWhenBotIsNull() {
        help.runSlashCommand(eventMock, null);

        verify(eventMock, never()).getInteraction();
    }
}