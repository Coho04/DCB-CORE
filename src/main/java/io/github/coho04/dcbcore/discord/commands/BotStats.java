package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import io.github.coho04.dcbcore.discord.Discord;
import io.github.coho04.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.Color;

@SuppressWarnings("ConstantConditions")
public class BotStats implements CommandInterface {

    @Override
    public CommandData commandData() {
        return Commands.slash("bot-stats", "Zeigt dir die Bot Statistiken");
    }

    @Override
    public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
        if (e == null || dcBot == null) {
            return;
        }
        Guild mainServer = e.getJDA().getGuildById(Discord.MAIN_GUILD);
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("**Server Stats**");
        embed.setFooter("@Golden-Developer", e.getJDA().getSelfUser().getAvatarUrl());
        embed.setColor(Color.MAGENTA);
        embed.addField("Server", String.valueOf(e.getJDA().getGuilds().size()), true);
        assert mainServer != null;
        embed.addField("Support-Server", mainServer.getName(), true);
        embed.addField("Bot-Owner", "@Golden-Developer", true);
        String url = mainServer.getDefaultChannel().createInvite().complete().getUrl();
        e.getInteraction()
                .replyEmbeds(embed.build())
                .addActionRow(Button.link(url, mainServer.getName()))
                .queue();
    }
}