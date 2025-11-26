package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import io.github.coho04.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.components.actionrow.ActionRow;

/**
 * The Donate class implements the CommandInterface to handle the "donate" slash command.
 * This command provides users with information on how to support the project through donations.
 */
public class Donate implements CommandInterface {

    /**
     * Returns the command data for the "donate" slash command.
     *
     * @return CommandData object containing the command information.
     */
    @Override
    public CommandData commandData() {
        return Commands.slash("donate", "Zeigt dir wie du uns Unterstützen kannst");
    }


    /**
     * Handles the execution of the "donate" slash command.
     * Sends a reply with a button linking to the donation page.
     *
     * @param e     The SlashCommandInteractionEvent triggered by the command.
     * @param dcBot The DCBot instance.
     */
    @Override
    public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
        if (e == null || dcBot == null) {
            return;
        }
        e.getInteraction()
                .reply("Wenn du uns etwas Spenden möchtest dann kannst du dies gerne in dem du unten auf den Button kickst machen! \n" + "Vielen Danke <3 !")
                .addComponents(ActionRow.of(Button.link("https://donate.golden-developer.de/", "Zur Spende")))
                .queue();
    }
}