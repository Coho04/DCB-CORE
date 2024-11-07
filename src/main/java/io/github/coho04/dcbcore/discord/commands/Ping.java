package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import io.github.coho04.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

/**
 * The Ping class implements the CommandInterface to handle the "ping" slash command.
 * This command allows users to check the bot's latency.
 */
public class Ping implements CommandInterface {

    /**
     * Returns the command data for the "ping" slash command.
     *
     * @return CommandData object containing the command information.
     */
    @Override
    public CommandData commandData() {
        return Commands.slash("ping", "Zeigt die Latenz des Bots an!");
    }

    /**
     * Handles the execution of the "ping" slash command.
     * Sends a reply with the bot's latency.
     *
     * @param e     The SlashCommandInteractionEvent triggered by the command.
     * @param dcBot The DCBot instance.
     */
    @Override
    public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
        if (e == null || dcBot == null) {
            return;
        }

        long time = System.currentTimeMillis();
        e.getInteraction().reply("Pong!").queue(response -> response.sendMessage("Pong:" + System.currentTimeMillis() + time + " ms").queue());
    }
}
