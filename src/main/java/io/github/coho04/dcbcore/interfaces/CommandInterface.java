package io.github.coho04.dcbcore.interfaces;

import io.github.coho04.dcbcore.DCBot;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

/**
 * The CommandInterface defines the structure for handling slash commands and auto-complete interactions.
 */
@SuppressWarnings("unused")
public interface CommandInterface {

    /**
     * Returns the command data for the slash command.
     *
     * @return CommandData object containing the command information.
     */
    CommandData commandData();

    /**
     * Handles the execution of the slash command.
     *
     * @param e     The SlashCommandInteractionEvent triggered by the command.
     * @param dcBot The DCBot instance.
     */
    void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot);

    /**
     * Handles the auto-complete interaction for the command.
     * This method is optional and can be overridden by implementing classes.
     *
     * @param e     The CommandAutoCompleteInteractionEvent triggered by the interaction.
     * @param dcBot The DCBot instance.
     */
    default void runCommandAutoComplete(CommandAutoCompleteInteractionEvent e, DCBot dcBot) {
    }

    /**
     * Checks if a member has a specific role.
     *
     * @param role   The role to check.
     * @param member The member whose roles are being checked.
     * @return true if the member has the specified role, false otherwise.
     */
    default boolean hasRole(Role role, Member member) {
        return member.getRoles().stream().filter(r -> r == role).map(r -> true).findFirst().orElse(false);
    }
}
