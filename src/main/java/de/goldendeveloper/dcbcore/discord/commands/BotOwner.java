package de.goldendeveloper.dcbcore.discord.commands;

import de.goldendeveloper.dcbcore.DCBot;
import de.goldendeveloper.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class BotOwner implements CommandInterface {

    @Override
    public CommandData commandData() {
        return Commands.slash("bot-owner", "Zeigt dir den Bot Programmierer");
    }

    @Override
    public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
        e.getInteraction().reply("Der Bot Owner ist die Developer Organisation Golden-Developer")
                .addActionRow(Button.link("https://dc.golden-developer.de/", "Zum Server"))
                .queue();
    }
}