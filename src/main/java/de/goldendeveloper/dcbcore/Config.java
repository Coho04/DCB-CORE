package de.goldendeveloper.dcbcore;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    public Dotenv dotenv;

    public Config() {
        dotenv = Dotenv.load();
    }

    public void setDotenv(Dotenv dotenv) {
        this.dotenv = dotenv;
    }

    public String getProjektVersion() {
        return loadProperties(this.getClass().getClassLoader().getResourceAsStream("project.properties")).getProperty("version");
    }

    public String getProjektName() {
        return loadProperties(this.getClass().getClassLoader().getResourceAsStream("project.properties")).getProperty("name");
    }

    public String getSentryDNS() {
        return dotenv.get("SENTRY_DNS");
    }

    public String getDiscordWebhook() {
        return dotenv.get("DISCORD_WEBHOOK");
    }

    public String getDiscordToken() {
        return dotenv.get("DISCORD_TOKEN");
    }

    public int getServerPort() {
        return Integer.parseInt(dotenv.get("SERVER_PORT"));
    }

    public String getServerHostname() {
        return dotenv.get("SERVER_HOSTNAME");
    }

    public Properties loadProperties(InputStream inputStream) {
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}