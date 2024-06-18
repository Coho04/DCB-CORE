package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import io.github.coho04.dcbcore.discord.Discord;
import io.github.coho04.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class Join implements CommandInterface {

    @Override
    public CommandData commandData() {
        return Commands.slash("join", "Zeigt dir wie du unserem Server beitreten kannst");
    }

    @Override
    public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
        Guild guild = e.getJDA().getGuildById(Discord.MAIN_GUILD);
        if (guild != null) {
            e.getInteraction().reply("Mit dem Button unten kannst du unserem Server beitreten!")
                    .addActionRow(Button.link(guild.getTextChannelById(Discord.WELCOME_CHANNEL).createInvite().complete().getUrl(), "Zum Server"))
                    .queue();
        } else {
            e.getInteraction().reply("ERROR: Server is NULL").queue();
        }
    }
}