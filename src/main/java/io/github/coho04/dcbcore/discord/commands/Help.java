package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import io.github.coho04.dcbcore.discord.events.ButtonClickListener;
import io.github.coho04.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The Help class implements the CommandInterface to handle the "help" slash command.
 * This command provides users with a paginated list of all available commands.
 */
public class Help implements CommandInterface {

    private static final int COMMANDS_PER_PAGE = 10;

    /**
     * Returns the command data for the "help" slash command.
     *
     * @return CommandData object containing the command information.
     */
    @Override
    public CommandData commandData() {
        return Commands.slash("help", "Zeigt dir alle Befehle");
    }

    /**
     * Handles the execution of the "help" slash command.
     * Sends a paginated list of all available commands.
     *
     * @param e     The SlashCommandInteractionEvent triggered by the command.
     * @param dcBot The DCBot instance.
     */
    @Override
    public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
        if (e == null || dcBot == null) {
            return;
        }

        List<Command> commands = e.getJDA().retrieveCommands().complete();
        int totalPages = (int) Math.ceil((double) commands.size() / COMMANDS_PER_PAGE);
        AtomicInteger currentPage = new AtomicInteger(0);

        List<MessageEmbed> pages = IntStream.range(0, totalPages).mapToObj(page -> {
            List<Command> paginatedCommands = commands.subList(page * COMMANDS_PER_PAGE, Math.min((page + 1) * COMMANDS_PER_PAGE, commands.size()));
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("Help Commands - Seite " + (page + 1))
                    .setColor(Color.MAGENTA)
                    .setFooter("@Golden-Developer", e.getJDA().getSelfUser().getAvatarUrl());

            paginatedCommands.forEach(c -> embed.addField("/" + c.getName(), c.getDescription(), true));
            return embed.build();
        }).collect(Collectors.toList());

        Button prev = Button.primary("core-prev", "Vorherige Seite").withDisabled(true);
        Button next = Button.primary("core-next", "Nächste Seite").withDisabled(pages.size() <= 1);

        e.replyEmbeds(pages.get(currentPage.get()))
                .addActionRow(
                        prev,
                        next,
                        Button.link("https://wiki.Golden-Developer.de", "Online Übersicht"),
                        Button.link("https://support.Golden-Developer.de", "Support Anfragen")
                ).queue(interactionHook -> interactionHook.retrieveOriginal().queue(message ->
                        e.getJDA().addEventListener(new ButtonClickListener(message, pages, currentPage, prev, next))));
    }
}
