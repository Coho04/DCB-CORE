package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import io.github.coho04.dcbcore.discord.Discord;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class ShutdownTest {

    private Shutdown shutdown;

    @Mock
    private DCBot dcBotMock;

    @Mock
    private Discord discordMock;

    @Mock
    private SlashCommandInteractionEvent eventMock;

    @Mock
    private SlashCommandInteraction interactionMock;

    @Mock
    private JDA jdaMock;

    @Mock
    private SelfUser selfUserMock;

    @Mock
    private ReplyCallbackAction replyActionMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        shutdown = new Shutdown();
    }

/*    @Test
    public void shouldRunSlashCommandSuccessfully() {
        // Mock the behavior of getDiscord() method of DCBot
        when(dcBotMock.getDiscord()).thenReturn(discordMock);

        // Mock the behavior of hasPermissions() method of Discord
        when(discordMock.hasPermissions(eventMock)).thenReturn(true);

        // Mock the behavior of getInteraction() method of SlashCommandInteractionEvent
        when(eventMock.getInteraction()).thenReturn(interactionMock);

        // Mock the behavior of getJDA() method of SlashCommandInteractionEvent
        when(eventMock.getJDA()).thenReturn(jdaMock);

        // Mock the behavior of getSelfUser() method of JDA
        when(jdaMock.getSelfUser()).thenReturn(selfUserMock);

        // Mock the behavior of getName() method of SelfUser
        when(selfUserMock.getName()).thenReturn("TestBot");

        // Mock the behavior of reply() method of SlashCommandInteraction
        when(interactionMock.reply(anyString())).thenReturn(replyActionMock);

        // Mock the behavior of queue() method of ReplyCallbackAction
        doNothing().when(replyActionMock).queue();

        // Mock the behavior of shutdown() method of JDA
        doNothing().when(jdaMock).shutdown();

        shutdown.runSlashCommand(eventMock, dcBotMock);

        // Verify interactions
        verify(dcBotMock, times(1)).getDiscord();
        verify(discordMock, times(1)).hasPermissions(eventMock);
        verify(interactionMock, times(1)).reply("Der Discord Bot [TestBot] wird nun heruntergefahren");
        verify(replyActionMock, times(1)).queue();
        verify(jdaMock, times(1)).shutdown();
    }*/

/*    @Test
    public void shouldNotRunSlashCommandWhenNoPermission() {
        // Mock the behavior of getDiscord() method of DCBot
        when(dcBotMock.getDiscord()).thenReturn(discordMock);

        // Mock the behavior of hasPermissions() method of Discord
        when(discordMock.hasPermissions(eventMock)).thenReturn(false);

        // Mock the behavior of getInteraction() method of SlashCommandInteractionEvent
        when(eventMock.getInteraction()).thenReturn(interactionMock);

        // Mock the behavior of reply() method of SlashCommandInteraction
        when(interactionMock.reply(anyString())).thenReturn(replyActionMock);

        // Mock the behavior of queue() method of ReplyCallbackAction
        doNothing().when(replyActionMock).queue();

        shutdown.runSlashCommand(eventMock, dcBotMock);

        // Verify interactions
        verify(dcBotMock, times(1)).getDiscord();
        verify(discordMock, times(1)).hasPermissions(eventMock);
        verify(interactionMock, times(1)).reply("Dazu hast du keine Rechte du musst für diesen Befehl der Bot Inhaber sein!");
        verify(replyActionMock, times(1)).queue();
        verify(jdaMock, times(0)).shutdown();  // Ensure shutdown() is not called
    }*/
}
