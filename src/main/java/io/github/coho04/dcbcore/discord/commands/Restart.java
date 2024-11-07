package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import io.github.coho04.dcbcore.interfaces.CommandInterface;
import io.sentry.Sentry;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The Restart class implements the CommandInterface to handle the "restart" slash command.
 * This command restarts the bot.
 */
public class Restart implements CommandInterface {

    /**
     * Returns the command data for the "restart" slash command.
     *
     * @return CommandData object containing the command information.
     */
    @Override
    public CommandData commandData() {
        return Commands.slash("restart", "Starte den Bot neu!");
    }

    /**
     * Handles the execution of the "restart" slash command.
     * Restarts the bot if the user has the necessary permissions.
     *
     * @param e     The SlashCommandInteractionEvent triggered by the command.
     * @param dcBot The DCBot instance.
     */
    @Override
    public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
        if (e == null || dcBot == null) {
            return;
        }

        if (dcBot.getDiscord().hasPermissions(e)) {
            e.getInteraction().reply("Der Discord Bot [" + e.getJDA().getSelfUser().getName() + "] wird nun neugestartet!").queue();
            restartBot(e.getJDA(), dcBot);
        } else {
            e.getInteraction().reply("Dazu hast du keine Rechte du musst für diesen Befehl der Bot Inhaber sein!").queue();
        }
    }

    /**
     * Restarts the bot by executing a new process with the specified JAR file.
     *
     * @param jda   The JDA instance.
     * @param dcBot The DCBot instance.
     */
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

    /**
     * Creates and returns a new process to restart the bot.
     *
     * @param dcBot      The DCBot instance.
     * @param path       The path to the directory containing the JAR file.
     * @param jarFileName The name of the JAR file.
     * @return The Process object representing the new process.
     * @throws IOException If an I/O error occurs.
     */
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

        return processBuilder.start();
    }
}