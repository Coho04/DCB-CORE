package de.goldendeveloper.dcbcore.discord.Commands;

import de.goldendeveloper.dcbcore.DCBot;
import de.goldendeveloper.dcbcore.interfaces.CommandInterface;
import io.sentry.Sentry;
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
            try {
                String[] commands = {"screen -AmdS " + dcBot.getConfig().getProjektName() + " java -Xms1096M -Xmx1096M -jar " + dcBot.getConfig().getProjektName() + "-" + dcBot.getConfig().getProjektVersion() + ".jar restart"};
                e.getInteraction().reply("Der Discord Bot [" + e.getJDA().getSelfUser().getName() + "] wird nun neugestartet!").queue();
                Process p = Runtime.getRuntime().exec(commands);
                p.waitFor();
                e.getJDA().shutdown();
            } catch (Exception ex) {
                Sentry.captureException(ex);
                ex.printStackTrace();
            }
        } else {
            e.getInteraction().reply("Dazu hast du keine Rechte du musst für diesen Befehl der Bot Inhaber sein!").queue();
        }
    }
}