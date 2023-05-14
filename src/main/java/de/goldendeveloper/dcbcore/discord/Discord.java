package de.goldendeveloper.dcbcore.discord;

import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import de.goldendeveloper.dcbcore.DCBot;
import de.goldendeveloper.dcbcore.discord.Commands.HelpCommand;
import de.goldendeveloper.dcbcore.discord.events.CoreEvents;
import io.sentry.Sentry;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Discord {

    private JDA bot;
    private final DCBot dcBot;
    public static HashMap<String, Class<CommandClass>> commands = new HashMap<>();

    public Discord(String BOT_TOKEN, DCBot dcBot) {
        commands.put("help", HelpCommand.class);
        this.dcBot = dcBot;
        try {
            bot = JDABuilder.createDefault(BOT_TOKEN)
                    .setChunkingFilter(ChunkingFilter.ALL)
                    .setMemberCachePolicy(MemberCachePolicy.ALL)
                    .enableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.ROLE_TAGS, CacheFlag.STICKER, CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.ONLINE_STATUS)
                    .enableIntents(GatewayIntent.GUILD_MESSAGE_REACTIONS,
                            GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                            GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_PRESENCES,
                            GatewayIntent.GUILD_MODERATION, GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                            GatewayIntent.GUILD_INVITES, GatewayIntent.DIRECT_MESSAGE_TYPING,
                            GatewayIntent.GUILD_MESSAGE_TYPING, GatewayIntent.GUILD_VOICE_STATES,
                            GatewayIntent.GUILD_WEBHOOKS, GatewayIntent.GUILD_MEMBERS,
                            GatewayIntent.GUILD_MESSAGE_TYPING)
                    .addEventListeners(new CoreEvents(dcBot))
                    .setAutoReconnect(true)
                    .setContextEnabled(true)
                    .build().awaitReady();
            registerDefaultCommand();
            if (dcBot.getDeployment()) {
                Online();
                dcBot.getServerCommunicator().startBot(bot);
            }
            bot.getPresence().setActivity(Activity.playing("/help | " + bot.getGuilds().size() + " Servern"));
        } catch (InterruptedException e) {
            Sentry.captureException(e);
            e.printStackTrace();
        }
    }

    private void registerDefaultCommand() {
        bot.upsertCommand(RegisterCommands.Ping, "Antwortet mit Pong").queue();
        bot.upsertCommand(RegisterCommands.CmdShutdown, "Stoppt den Discord Bot!").queue();
        bot.upsertCommand(RegisterCommands.BotStats, "Zeigt dir die Stats des Bots").queue();
        bot.upsertCommand(RegisterCommands.CmdRestart, "Startet den Discord Bot neu!").queue();
        bot.upsertCommand(RegisterCommands.Donate, "Zeigt dir eine Spende möglichkeit").queue();
        bot.upsertCommand(RegisterCommands.Bot_Owner, "Zeigt dir den Bot Programmierer").queue();
        bot.upsertCommand(RegisterCommands.Help, "Zeigt eine Liste von möglichen Befehlen").queue();
        bot.upsertCommand(RegisterCommands.Invite, "Du wirst eingeladen auf unseren Discord").queue();
        bot.upsertCommand(RegisterCommands.Join, "Der Bot sendet dir einen Link um ihn einzuladen").queue();
        bot.upsertCommand(RegisterCommands.Error_report, "Reporte einen Bot fehler").addOption(OptionType.STRING, "error", "Schildere hier deinen gefundenen Bot Fehler", true).queue();
    }

    public void registerCommands(List<CommandData> commandDataList) {
        commandDataList.forEach(commanddata -> bot.upsertCommand(commanddata).queue());
    }

    public void sendErrorMessage(String Error) {
        WebhookEmbedBuilder embed = new WebhookEmbedBuilder();
        embed.setAuthor(new WebhookEmbed.EmbedAuthor(getBot().getSelfUser().getName(), getBot().getSelfUser().getAvatarUrl(), "https://Golden-Developer.de"));
        embed.addField(new WebhookEmbed.EmbedField(false, "[ERROR]", Error));
        embed.setColor(0xFF0000);
        embed.setFooter(new WebhookEmbed.EmbedFooter("@Golden-Developer", getBot().getSelfUser().getAvatarUrl()));
        new WebhookClientBuilder(dcBot.getConfig().getDiscordWebhook()).build().send(embed.build());
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
}
