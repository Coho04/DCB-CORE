package de.goldendeveloper.dcbcore.discord;

import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import de.goldendeveloper.dcbcore.DCBot;
import de.goldendeveloper.dcbcore.discord.Commands.*;
import de.goldendeveloper.dcbcore.discord.events.CoreEvents;
import de.goldendeveloper.dcbcore.interfaces.CommandInterface;
import io.sentry.Sentry;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;

public class Discord {

    public static final long WELCOME_CHANNEL = 817500165866782772L;
    public static final long MAIN_GUILD = 817500165866782770L;

    private JDA bot;
    private final DCBot dcBot;
    private final LinkedList<CommandInterface> commands;

    public Discord(String BOT_TOKEN, DCBot dcBot) {
        this.commands = dcBot.getCommandDataList();
        this.dcBot = dcBot;
        try {
            JDABuilder botBuilder = JDABuilder.createDefault(BOT_TOKEN)
                    .setChunkingFilter(ChunkingFilter.ALL)
                    .setMemberCachePolicy(MemberCachePolicy.ALL)
                    .enableCache(
                            CacheFlag.MEMBER_OVERRIDES, CacheFlag.ROLE_TAGS,
                            CacheFlag.STICKER, CacheFlag.ACTIVITY,
                            CacheFlag.CLIENT_STATUS, CacheFlag.ONLINE_STATUS
                    )
                    .enableIntents(
                            GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.GUILD_EMOJIS_AND_STICKERS, GatewayIntent.DIRECT_MESSAGES,
                            GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MODERATION,
                            GatewayIntent.DIRECT_MESSAGE_REACTIONS, GatewayIntent.GUILD_INVITES,
                            GatewayIntent.DIRECT_MESSAGE_TYPING, GatewayIntent.GUILD_MESSAGE_TYPING,
                            GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_WEBHOOKS,
                            GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGE_TYPING,
                            GatewayIntent.MESSAGE_CONTENT
                    )
                    .addEventListeners(new CoreEvents(dcBot))
                    .setAutoReconnect(true)
                    .setContextEnabled(true);
            if (dcBot.getEvents().size() > 0) {
                dcBot.getEvents().forEach(botBuilder::addEventListeners);
            }
            if (dcBot.getGatewayIntentList().size() > 0) {
                botBuilder.enableIntents(dcBot.getGatewayIntentList());
            }
            bot = botBuilder.build().awaitReady();
            this.registerDefaultCommand();
            if (dcBot.getDeployment()) {
                Online();
                if (dcBot.getWithServerCommunicator()) {
                    dcBot.getServerCommunicator().startBot(bot);
                }
            }
            bot.getPresence().setActivity(Activity.playing("/help | " + bot.getGuilds().size() + " Servern"));
            commands.stream().filter(Objects::nonNull).forEach(commandInterface -> bot.upsertCommand(commandInterface.commandData()).queue());
        } catch (InterruptedException e) {
            Sentry.captureException(e);
            e.printStackTrace();
        }
    }

    private void registerDefaultCommand() {
        Collections.addAll(commands,new BotStats(), new BotOwner(), new Donate(), new Help(), new Invite(), new Join(), new Ping(), new Restart(), new Shutdown());
    }

    public LinkedList<CommandInterface> getCommands() {
        return commands;
    }

    public JDA getBot() {
        return bot;
    }

    private void Online() {
        WebhookEmbedBuilder embed = new WebhookEmbedBuilder();
        if (dcBot.getRestart()) {
            embed.setColor(0x33FFFF);
            embed.addField(new WebhookEmbed.EmbedField(false, "[Status]", "Neustart erfolgreich"));
        } else {
            embed.setColor(0x00FF00);
            embed.addField(new WebhookEmbed.EmbedField(false, "[Status]", "ONLINE"));
        }
        embed.setAuthor(new WebhookEmbed.EmbedAuthor(getBot().getSelfUser().getName(), getBot().getSelfUser().getAvatarUrl(), "https://Golden-Developer.de"));
        embed.addField(new WebhookEmbed.EmbedField(false, "Gestartet als", bot.getSelfUser().getName()));
        embed.addField(new WebhookEmbed.EmbedField(false, "Server", Integer.toString(bot.getGuilds().size())));
        embed.addField(new WebhookEmbed.EmbedField(false, "Status", "\uD83D\uDFE2 Gestartet"));
        embed.addField(new WebhookEmbed.EmbedField(false, "Version", dcBot.getConfig().getProjektVersion()));
        embed.setFooter(new WebhookEmbed.EmbedFooter("@Golden-Developer", getBot().getSelfUser().getAvatarUrl()));
        embed.setTimestamp(new Date().toInstant());
        new WebhookClientBuilder(dcBot.getConfig().getDiscordWebhook()).build().send(embed.build());
    }

    public Boolean hasPermissions(SlashCommandInteractionEvent e) {
//        513306244371447828L ist die ID von Collin
//        428811057700536331L ist die ID von Nick
        return e.getUser() == e.getJDA().getUserById(428811057700536331L) || e.getUser() == e.getJDA().getUserById(513306244371447828L);
    }
}
