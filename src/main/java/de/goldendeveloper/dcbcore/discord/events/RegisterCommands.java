package de.goldendeveloper.dcbcore.discord.events;

import io.sentry.Sentry;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.util.List;

public class RegisterCommands extends ListenerAdapter {

    public static final String Donate = "donate";
    public static final String Ping = "ping";
    public static final String Invite = "invite";
    public static final String Join = "join";
    public static final String Bot_Owner = "bot-owner";
    public static final String Help = "help";
    public static final String BotStats = "bot-stats";
    public static final String CmdShutdown = "shutdown";
    public static final String CmdRestart = "restart";

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {

        String cmd = e.getName();
        switch (cmd) {
            case Ping -> this.Ping(e);
            case Help -> this.Help(e);
            case Invite -> this.ServerInvite(e);
            case CmdRestart -> this.Restart(e);
            case BotStats -> this.BotStats(e);
            case CmdShutdown -> this.Shutdown(e);
            case Donate -> e.getInteraction().reply("Wenn du uns etwas Spenden möchtest dann kannst du dies gerne in dem du unten auf den Button kickst machen! \n" + "Vielen Danke <3 !").addActionRow(Button.link("https://donate.golden-developer.de/", "Zur Spende")).queue();
            case Bot_Owner -> e.getInteraction().reply("Der Bot Owner ist die Organisation Golden-Developer").addActionRow(Button.link("https://dc.golden-developer.de/", "Zum Server")).queue();
            case Join -> e.getInteraction().reply("Mit dem Button kannst du mich auf deinen Server einladen!").addActionRow(Button.link(e.getJDA().setRequiredScopes("applications.commands").getInviteUrl(Permission.ADMINISTRATOR), "Hier Klicken")).queue();
        }
    }

    public void Shutdown(SlashCommandInteractionEvent e) {
        User _Coho04_ = e.getJDA().getUserById(_Coho04_MEMBER);
        User zRazzer = e.getJDA().getUserById("428811057700536331");
        if (e.getUser() == zRazzer || e.getUser() == _Coho04_) {
            e.getInteraction().reply("Der Discord Bot [" + e.getJDA().getSelfUser().getName() + "] wird nun heruntergefahren").queue();
            e.getJDA().shutdown();
        } else {
            e.getInteraction().reply("Dazu hast du keine Rechte du musst für diesen Befehl der Bot Inhaber sein!").queue();
        }
    }

    public void Restart(SlashCommandInteractionEvent e) {
        if (hasPermissions(e)) {
            try {
                e.getInteraction().reply("Der Discord Bot [" + e.getJDA().getSelfUser().getName() + "] wird nun neugestartet!").queue();
                Process p = Runtime.getRuntime().exec("screen -AmdS " + Main.getConfig().getProjektName() + " java -Xms1096M -Xmx1096M -jar " + Main.getConfig().getProjektName() + "-" + Main.getConfig().getProjektVersion() + ".jar restart");
                p.waitFor();
                e.getJDA().shutdown();
            } catch (Exception ex) {
                Sentry.captureException(ex);
                ex.printStackTrace();
            }
        } else {
            e.getInteraction().reply("Dazu hast du keine Rechte du musst für diesen Befehl der Bot inhaber sein!").queue();
        }
    }

    public void ServerInvite(SlashCommandInteractionEvent e) {
        Guild g = e.getJDA().getGuildById(Main.getConfig().getDiscordServer());
        if (g != null) {
            e.getInteraction().reply("Mit dem Button unten kannst du unserem Server beitreten!")
                    .addActionRow(Button.link(g.getTextChannelById(747208323761176586L).createInvite().complete().getUrl(), "Zum Server"))
                    .queue();
        } else {
            e.getInteraction().reply(hasError("Guild is NULL")).queue();
        }
    }

    public void Ping(SlashCommandInteractionEvent e) {
        long time = System.currentTimeMillis();
        e.getInteraction().reply("Pong!").queue(response -> response.sendMessage("Pong:" + System.currentTimeMillis() + time + " ms").queue());
    }

    public void Help(SlashCommandInteractionEvent e) {
        List<Command> cmd = Main.getDiscord().getBot().retrieveCommands().complete();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("**Help Commands**");
        embed.setColor(Color.MAGENTA);
        embed.setFooter("@Golden-Developer", e.getJDA().getSelfUser().getAvatarUrl());
        for (Command c : cmd) {
            embed.addField("/" + c.getName(), c.getDescription(), true);
        }
        e.getInteraction().replyEmbeds(embed.build())
                .addActionRow(
                        Button.link("https://wiki.Golden-Developer.de", "Online Übersicht"),
                        Button.link("https://support.Golden-Developer.de", "Support Anfragen")
                ).queue();
    }

    public void BotStats(SlashCommandInteractionEvent e) {
        Guild MainServer = e.getJDA().getGuildById(Main.getConfig().getDiscordServer());
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("**Server Stats**");
        embed.setFooter("@Golden-Developer", e.getJDA().getSelfUser().getAvatarUrl());
        embed.setColor(Color.MAGENTA);
        embed.addField("Server", String.valueOf(e.getJDA().getGuilds().size()), true);
        embed.addField("Support-Server", MainServer.getName(), true);
        embed.addField("Bot-Owner", "@Golden-Developer", true);
        e.getInteraction().replyEmbeds(embed.build()).addActionRow(Button.link(MainServer.getDefaultChannel().createInvite().complete().getUrl(), MainServer.getName())).queue();
    }

    public Boolean hasPermissions(SlashCommandInteractionEvent e) {
        User _Coho04_ = e.getJDA().getUserById("513306244371447828");
        User zRazzer = e.getJDA().getUserById("428811057700536331");
        return e.getUser() == zRazzer || e.getUser() == _Coho04_;
    }

    public static String hasError(String error) {
        return "[ERROR]: Es ist ein Fehler aufgetreten bitte melden den Grund mit /error-report \n" + "Fehler: " + error;
    }
}
