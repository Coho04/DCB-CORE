package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import io.github.coho04.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class Donate implements CommandInterface {

    @Override
    public CommandData commandData() {
        return Commands.slash("donate", "Zeigt dir wie du uns Unterstützen kannst");
    }

    @Override
    public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
        e.getInteraction()
                .reply("Wenn du uns etwas Spenden möchtest dann kannst du dies gerne in dem du unten auf den Button kickst machen! \n" + "Vielen Danke <3 !")
                .addActionRow(Button.link("https://donate.golden-developer.de/", "Zur Spende"))
                .queue();
    }
}