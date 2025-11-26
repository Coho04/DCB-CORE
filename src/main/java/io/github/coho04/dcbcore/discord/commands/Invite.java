package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import io.github.coho04.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.components.actionrow.ActionRow;

/**
 * The Invite class implements the CommandInterface to handle the "invite" slash command.
 * This command allows users to invite the bot to their server.
 */
public class Invite implements CommandInterface {

    /**
     * Returns the command data for the "invite" slash command.
     *
     * @return CommandData object containing the command information.
     */
    @Override
    public CommandData commandData() {
        return Commands.slash("invite", "Lade den Bot auf deinen Server ein!");
    }

    /**
     * Handles the execution of the "invite" slash command.
     * Sends a reply with a button to invite the bot to the server.
     *
     * @param e     The SlashCommandInteractionEvent triggered by the command.
     * @param dcBot The DCBot instance.
     */
    @Override
    public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
        if (e == null || dcBot == null) {
            return;
        }

        e.getInteraction().reply("Mit dem Button kannst du mich auf deinen Server einladen!")
                .addComponents(ActionRow.of(
                        Button.link(e.getJDA().setRequiredScopes("applications.commands")
                                .getInviteUrl(Permission.ADMINISTRATOR), "Hier Klicken")
                )).queue();

    }
}