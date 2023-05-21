package de.goldendeveloper.dcbcore.discord.Commands;

import de.goldendeveloper.dcbcore.DCBot;
import de.goldendeveloper.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.Color;
import java.util.List;

public class Help implements CommandInterface {

    @Override
    public CommandData commandData() {
        return Commands.slash("help", "Zeigt dir alle Befehle");
    }

    @Override
    public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
        List<Command> cmd = e.getJDA().retrieveCommands().complete();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("**Help Commands**");
        embed.setColor(Color.MAGENTA);
        embed.setFooter("@Golden-Developer", e.getJDA().getSelfUser().getAvatarUrl());
        cmd.forEach(c -> embed.addField("/" + c.getName(), c.getDescription(), true));
        e.getInteraction().replyEmbeds(embed.build())
                .addActionRow(
                        Button.link("https://wiki.Golden-Developer.de", "Online Übersicht"),
                        Button.link("https://support.Golden-Developer.de", "Support Anfragen")
                ).queue();
    }
}
