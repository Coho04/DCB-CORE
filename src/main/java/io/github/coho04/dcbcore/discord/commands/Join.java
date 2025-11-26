package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import io.github.coho04.dcbcore.discord.Discord;
import io.github.coho04.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.components.actionrow.ActionRow;

/**
 * The Join class implements the CommandInterface to handle the "join" slash command.
 * This command provides users with a way to join the server.
 */
public class Join implements CommandInterface {

    /**
     * Returns the command data for the "join" slash command.
     *
     * @return CommandData object containing the command information.
     */
    @Override
    public CommandData commandData() {
        return Commands.slash("join", "Zeigt dir wie du unserem Server beitreten kannst");
    }

    /**
     * Handles the execution of the "join" slash command.
     * Sends a reply with a button to join the server.
     *
     * @param e     The SlashCommandInteractionEvent triggered by the command.
     * @param dcBot The DCBot instance.
     */
    @Override
    public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
        if (e == null || dcBot == null) {
            return;
        }

        Guild guild = e.getJDA().getGuildById(Discord.MAIN_GUILD);
        if (guild != null) {
            e.getInteraction().reply("Mit dem Button unten kannst du unserem Server beitreten!")
                    .addComponents(ActionRow.of(Button.link(guild.getTextChannelById(Discord.WELCOME_CHANNEL).createInvite().complete().getUrl(), "Zum Server")))
                    .queue();
        } else {
            e.getInteraction().reply("ERROR: Server is NULL").queue();
        }
    }
}