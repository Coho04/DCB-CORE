package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import io.github.coho04.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

/**
 * The Shutdown class implements the CommandInterface to handle the "shutdown" slash command.
 * This command initiates the shutdown process for the bot.
 */
public class Shutdown implements CommandInterface {

    /**
     * Returns the command data for the "shutdown" slash command.
     *
     * @return CommandData object containing the command information.
     */
    @Override
    public CommandData commandData() {
        return Commands.slash("shutdown", "Starte den Shutdown-Prozess für den Bot.");
    }

    /**
     * Handles the execution of the "shutdown" slash command.
     * Shuts down the bot if the user has the necessary permissions.
     *
     * @param e     The SlashCommandInteractionEvent triggered by the command.
     * @param dcBot The DCBot instance.
     */
    @Override
    public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
        if (e == null || dcBot == null) {
            return;
        }

        if (dcBot.getDiscord().hasPermissions(e)) {
            e.getInteraction().reply("Der Discord Bot [" + e.getJDA().getSelfUser().getName() + "] wird nun heruntergefahren").queue();
            e.getJDA().shutdown();
        } else {
            e.getInteraction().reply("Dazu hast du keine Rechte du musst für diesen Befehl der Bot Inhaber sein!").queue();
        }
    }
}
