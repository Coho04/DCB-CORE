package de.goldendeveloper.dcbcore;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.util.Properties;

public class Config {

    private final Dotenv dotenv;

    public Config() {
        dotenv = Dotenv.load();
    }

    public String getProjektVersion() {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("project.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty("version");
    }

    public String getProjektName() {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("project.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty("name");
    }

    public String getSentryDNS() {
        return dotenv.get("SENTRY_DNS");
    }

    public String getDiscordWebhook() {
        return dotenv.get("DISCORD_WEBHOOK");
    }

    public int getMysqlPort() {
        return Integer.parseInt(dotenv.get("MYSQL_PORT"));
    }

    public String getDiscordServer() {
        return dotenv.get("DISCORD_SERVER");
    }

    public String getDiscordToken() {
        return dotenv.get("DISCORD_TOKEN");
    }

    public String getMysqlHostname() {
        return dotenv.get("MYSQL_HOSTNAME");
    }

    public String getMysqlPassword() {
        return dotenv.get("MYSQL_PASSWORD");
    }

    public String getMysqlUsername() {
        return dotenv.get("MYSQL_USERNAME");
    }

    public int getServerPort() {
        return Integer.parseInt(dotenv.get("SERVER_PORT"));
    }

    public String getServerHostname() {
        return dotenv.get("SERVER_HOSTNAME");
    }
}