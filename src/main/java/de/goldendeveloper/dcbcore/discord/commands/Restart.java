package de.goldendeveloper.dcbcore.discord.commands;

import de.goldendeveloper.dcbcore.DCBot;
import de.goldendeveloper.dcbcore.interfaces.CommandInterface;
import io.sentry.Sentry;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

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
            String jarFileName = dcBot.getConfig().getProjektName() + "-" + dcBot.getConfig().getProjektVersion() + ".jar";
            Path path = Paths.get(".").toAbsolutePath().normalize();
            Process process = getProcess(dcBot, path, jarFileName);

            try (InputStream errorStream = process.getErrorStream();
                 BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
                 InputStream inputStream = process.getInputStream();
                 BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream))) {

                String line;
                System.out.println("Standard Output:");
                while ((line = inputReader.readLine()) != null) {
                    System.out.println(line);
                }

                System.out.println("Error Output:");
                while ((line = errorReader.readLine()) != null) {
                    System.err.println(line);
                }
            }

            int exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode);

            jda.shutdown();
        } catch (Exception exception) {
            Sentry.captureException(exception);
            System.out.println(exception.getMessage());
        }
    }

    private static @NotNull Process getProcess(DCBot dcBot, Path path, String jarFileName) throws IOException {
        String absolutePath = path + File.separator + jarFileName;

        String screenPath = "/usr/bin/screen";
        String javaPath = "/usr/bin/java";

        String[] commands = {
                screenPath,
                "-AmdS",
                dcBot.getConfig().getProjektName(),
                javaPath,
                "-Xms1096M",
                "-Xmx1096M",
                "-jar",
                absolutePath,
                "restart"
        };

        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        processBuilder.directory(new File(String.valueOf(path)));

        Process process = processBuilder.start();
        return process;
    }
}