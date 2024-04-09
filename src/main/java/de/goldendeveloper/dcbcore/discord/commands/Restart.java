package de.goldendeveloper.dcbcore.discord.commands;

import de.goldendeveloper.dcbcore.DCBot;
import de.goldendeveloper.dcbcore.interfaces.CommandInterface;
import io.sentry.Sentry;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class Restart implements CommandInterface {

    @Override
    public CommandData commandData() {
        return Commands.slash("restart", "Starte den Bot neu!");
    }

    @Override
    public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
        if (dcBot.getDiscord().hasPermissions(e)) {
            e.getInteraction().reply("Der Discord Bot [" + e.getJDA().getSelfUser().getName() + "] wird nun neugestartet!").queue();
            restartBot(e.getJDA(), dcBot);
        } else {
            e.getInteraction().reply("Dazu hast du keine Rechte du musst für diesen Befehl der Bot Inhaber sein!").queue();
        }
    }

    public static void restartBot(JDA jda, DCBot dcBot) {
        try {
            String[] commands = {"/usr/bin/screen -AmdS " + dcBot.getConfig().getProjektName() + " java -Xms1096M -Xmx1096M -jar " + dcBot.getConfig().getProjektName() + "-" + dcBot.getConfig().getProjektVersion() + ".jar restart"};
            Process p = Runtime.getRuntime().exec(commands);
            p.waitFor();
            jda.shutdown();
        } catch (Exception exception) {
            Sentry.captureException(exception);
            System.out.println(exception.getMessage());
        }
    }
}