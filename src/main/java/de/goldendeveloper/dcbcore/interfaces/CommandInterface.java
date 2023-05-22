package de.goldendeveloper.dcbcore.interfaces;

import de.goldendeveloper.dcbcore.DCBot;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

@SuppressWarnings("unused")
public interface CommandInterface {

    CommandData commandData();

    void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot);

    default void runCommandAutoComplete(CommandAutoCompleteInteractionEvent e, DCBot dcBot) {
    }

    default boolean hasRole(Role role, Member m) {
        return m.getRoles().stream().filter(r -> r == role).map(r -> true).findFirst().orElse(false);
    }
}
