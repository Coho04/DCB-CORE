package de.goldendeveloper.dcbcore.interfaces;

import de.goldendeveloper.dcbcore.DCBot;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public interface CommandInterface {

    CommandData commandData();

    void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot);

    @SuppressWarnings("unused")
    default void runCommandAutoComplete(CommandAutoCompleteInteractionEvent e, DCBot dcBot) {
    }
}
