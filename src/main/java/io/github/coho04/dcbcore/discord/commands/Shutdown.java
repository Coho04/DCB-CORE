package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import io.github.coho04.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class Shutdown implements CommandInterface {

    @Override
    public CommandData commandData() {
        return Commands.slash("shutdown", "Starte den Shutdown-Prozess für den Bot.");
    }

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
