package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import io.github.coho04.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class Ping implements CommandInterface {

    @Override
    public CommandData commandData() {
        return Commands.slash("ping", "Zeigt die Latenz des Bots an!");
    }

    @Override
    public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
        long time = System.currentTimeMillis();
        e.getInteraction().reply("Pong!").queue(response -> response.sendMessage("Pong:" + System.currentTimeMillis() + time + " ms").queue());
    }
}
