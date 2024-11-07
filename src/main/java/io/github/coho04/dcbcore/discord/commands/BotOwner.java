package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import io.github.coho04.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

/**
 * The BotOwner class implements the CommandInterface to handle the "bot-owner" slash command.
 * This command provides users with information about the bot owner.
 */
public class BotOwner implements CommandInterface {

    /**
     * Returns the command data for the "bot-owner" slash command.
     *
     * @return CommandData object containing the command information.
     */
    @Override
    public CommandData commandData() {
        return Commands.slash("bot-owner", "Zeigt dir den Bot Programmierer");
    }

    /**
     * Handles the execution of the "bot-owner" slash command.
     * Sends a reply with information about the bot owner.
     *
     * @param e     The SlashCommandInteractionEvent triggered by the command.
     * @param dcBot The DCBot instance.
     */
    @Override
    public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
        if (e == null || dcBot == null) {
            return;
        }

        e.getInteraction().reply("Der Bot Owner ist die Developer Organisation Golden-Developer")
                .addActionRow(Button.link("https://dc.golden-developer.de/", "Zum Server"))
                .queue();
    }
}