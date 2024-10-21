package io.github.coho04.dcbcore.discord.events;

import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import io.github.coho04.dcbcore.DCBot;
import io.sentry.Sentry;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class CoreEvents extends ListenerAdapter {

    private final DCBot dcBot;

    public CoreEvents(DCBot dcBot) {
        this.dcBot = dcBot;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        dcBot.getDiscord().getCommands().stream().filter(command -> command.commandData().getName().equals(e.getName())).findFirst().ifPresent(command -> command.runSlashCommand(e, dcBot));
    }

    @Override
    public void onCommandAutoCompleteInteraction(@NotNull CommandAutoCompleteInteractionEvent e) {
        dcBot.getDiscord().getCommands().stream().filter(command -> command.commandData().getName().equals(e.getName())).findFirst().ifPresent(command -> command.runCommandAutoComplete(e, dcBot));
    }

    @Override
    public void onShutdown(@NotNull ShutdownEvent e) {
        try {
            if (dcBot.getDeployment()) {
                WebhookEmbedBuilder embed = new WebhookEmbedBuilder();
                embed.setAuthor(new WebhookEmbed.EmbedAuthor(dcBot.getDiscord().getBot().getSelfUser().getName(), dcBot.getDiscord().getBot().getSelfUser().getAvatarUrl(), "https://Golden-Developer.de"));
                embed.addField(new WebhookEmbed.EmbedField(false, "[Status]", "Offline"));
                embed.addField(new WebhookEmbed.EmbedField(false, "Gestoppt als", dcBot.getDiscord().getBot().getSelfUser().getName()));
                embed.addField(new WebhookEmbed.EmbedField(false, "Server", Integer.toString(dcBot.getDiscord().getBot().getGuilds().size())));
                embed.addField(new WebhookEmbed.EmbedField(false, "Status", "\uD83D\uDD34 Offline"));
                embed.addField(new WebhookEmbed.EmbedField(false, "Version", dcBot.getConfig().getProjektVersion()));
                embed.setFooter(new WebhookEmbed.EmbedFooter("@Golden-Developer", dcBot.getDiscord().getBot().getSelfUser().getAvatarUrl()));
                embed.setTimestamp(new Date().toInstant());
                embed.setColor(0xFF0000);
                new WebhookClientBuilder(dcBot.getConfig().getDiscordWebhook()).build().send(embed.build()).thenRun(() -> System.exit(0));
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            Sentry.captureException(exception);
        }
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent e) {
        e.getJDA().getPresence().setActivity(Activity.playing("/help | " + e.getJDA().getGuilds().size() + " Servern"));
    }

    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent e) {
        e.getJDA().getPresence().setActivity(Activity.playing("/help | " + e.getJDA().getGuilds().size() + " Servern"));
    }
}
