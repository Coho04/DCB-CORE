package io.github.coho04.dcbcore;

import io.github.coho04.dcbcore.discord.Discord;
import io.github.coho04.dcbcore.errors.SentryHandler;
import io.github.coho04.dcbcore.interfaces.CommandInterface;
import io.sentry.ITransaction;
import io.sentry.Sentry;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unused")
public class DCBot {

    private final LinkedList<ListenerAdapter> events = new LinkedList<>();
    private final LinkedList<CommandInterface> commandDataList = new LinkedList<>();
    private final LinkedList<CommandInterface> removedCommandDataList = new LinkedList<>();
    private final LinkedList<GatewayIntent> gatewayIntentList = new LinkedList<>();

    private Discord discord;
    private final Config config;
    private boolean restart = false;
    private boolean deployment = true;
    private final String[] args;

    public DCBot(
            String[] args,
            boolean withServerCommunicator,
            LinkedList<ListenerAdapter> events,
            LinkedList<CommandInterface> commandDataList,
            LinkedList<GatewayIntent> gatewayIntentList,
            LinkedList<CommandInterface> removedCommandDataList,
            boolean withLavaLink
    ) {
        if (args.length >= 1 && args[0].equalsIgnoreCase("restart")) {
            restart = true;
        }
        this.args = args;
        this.config = new Config();
        String device = System.getProperty("os.name").split(" ")[0];
        if (device.equalsIgnoreCase("windows") || device.equalsIgnoreCase("Mac")) {
            deployment = false;
        }
        if (events != null)
            this.events.addAll(events);
        if (commandDataList != null)
            this.commandDataList.addAll(commandDataList);
        if (gatewayIntentList != null)
            this.gatewayIntentList.addAll(gatewayIntentList);
        if (removedCommandDataList != null)
            this.removedCommandDataList.addAll(removedCommandDataList);


        setupBot(withServerCommunicator, withLavaLink);
    }

    private boolean detectDeployment() {
        String device = System.getProperty("os.name").split(" ")[0];
        return !device.equalsIgnoreCase("windows") && !device.equalsIgnoreCase("Mac");
    }

    private void setupBot(boolean withServerCommunicator, boolean withLavaLink) {
        try {
            SentryHandler sentryHandler = new SentryHandler(config.getSentryDNS(), this);
            ITransaction transaction = Sentry.startTransaction("Application()", "task");
            discord = new Discord(config.getDiscordToken(), this, withLavaLink);
            transaction.finish();
        } catch (Exception e) {
            Sentry.captureException(e);
            System.out.println(e.getMessage());
        }
    }

    public void setDeployment(Boolean deployment) {
        this.deployment = deployment;
    }

    public void setDiscord(Discord discord) {
        this.discord = discord;
    }

    public void setRestart(Boolean restart) {
        this.restart = restart;
    }

    public String[] getArgs() {
        return args;
    }

    public LinkedList<CommandInterface> getCommandDataList() {
        return commandDataList;
    }

    public LinkedList<CommandInterface> getRemovedCommandDataList() {
        return removedCommandDataList;
    }

    public Discord getDiscord() {
        return discord;
    }

    public Config getConfig() {
        return config;
    }

    public Boolean getRestart() {
        return restart;
    }

    public Boolean getDeployment() {
        return deployment;
    }

    public LinkedList<ListenerAdapter> getEvents() {
        return events;
    }

    public LinkedList<GatewayIntent> getGatewayIntentList() {
        return gatewayIntentList;
    }

    List<String> getGuildIDsFromGuilds(JDA jda) {
        List<String> ids = new ArrayList<>();
        jda.getGuilds().forEach(guild -> ids.add(guild.getId()));
        return ids;
    }

    List<String> getCommandNameFromCommands(List<Command> commands) {
        List<String> commandNames = new ArrayList<>();
        commands.forEach(command -> commandNames.add(command.getName()));
        return commandNames;
    }
}
