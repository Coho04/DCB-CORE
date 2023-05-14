package de.goldendeveloper.dcbcore;

import de.goldendeveloper.dcbcore.discord.Discord;
import de.goldendeveloper.dcbcore.errors.SentryHandler;
import io.sentry.ITransaction;
import io.sentry.Sentry;
import io.sentry.SpanStatus;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.List;

public class DCBot {

    private Discord discord;
    private final Config config;
    private MysqlConnection mysqlConnection;
    private ServerCommunicator serverCommunicator;

    private final Boolean withMysqlConnection;
    private final Boolean withServerCommunicator;

    private static Boolean restart = false;
    private static Boolean deployment = true;


    public DCBot(String[] args, boolean withMysqlConnection, boolean withServerCommunicator) {
        if (args.length >= 1 && args[0].equalsIgnoreCase("restart")) {
            restart = true;
        }
        this.withMysqlConnection = withMysqlConnection;
        this.withServerCommunicator = withServerCommunicator;
        String device = System.getProperty("os.name").split(" ")[0];
        if (device.equalsIgnoreCase("windows") || device.equalsIgnoreCase("Mac")) {
            deployment = false;
        }
        config = new Config();
        new SentryHandler(config.getSentryDNS(), this);
        ITransaction transaction = Sentry.startTransaction("Application()", "task");
        try {
            Application();
        } catch (Exception e) {
            transaction.setThrowable(e);
            transaction.setStatus(SpanStatus.INTERNAL_ERROR);
        } finally {
            transaction.finish();
        }
    }

    public void Application() {
        try {
            if (getDeployment() && withServerCommunicator) {
                serverCommunicator = new ServerCommunicator(config.getServerHostname(), config.getServerPort());
            }
            if (withMysqlConnection) {
                mysqlConnection = new MysqlConnection(config.getMysqlHostname(), config.getMysqlUsername(), config.getMysqlPassword(), config.getMysqlPort());
            }
            discord = new Discord(config.getDiscordToken(), this);
        } catch (Exception exception) {
            exception.printStackTrace();
            Sentry.captureException(exception);
        }
    }

    public void registerCommands(List<CommandData> commandDataList) {
        discord.registerCommands(commandDataList);
    }

    public Discord getDiscord() {
        return discord;
    }

    public Config getConfig() {
        return config;
    }

    public MysqlConnection getMysqlConnection() {
        return mysqlConnection;
    }

    public Boolean getRestart() {
        return restart;
    }

    public Boolean getDeployment() {
        return deployment;
    }

    public ServerCommunicator getServerCommunicator() {
        return serverCommunicator;
    }
}
