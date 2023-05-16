package de.goldendeveloper.dcbcore.discord.Commands;

import de.goldendeveloper.dcbcore.DCBot;
import de.goldendeveloper.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class ErrorReport implements CommandInterface {
    @Override
    public CommandData commandData() {
        return Commands.slash("error-report", "Melde einen Fehler")
                .addOption(OptionType.STRING, "error", "Schildere hier deinen gefundenen Bot Fehler", true);
    }

    @Override
    public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
    }
}