package de.goldendeveloper.dcbcore;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    public Dotenv dotenv;
    public static String path = null;

    public Config() {
        if(path != null) {
            dotenv = Dotenv.configure().directory(path).load();
        } else {
            dotenv = Dotenv.load();
        }
    }

    @SuppressWarnings("unused")
    public void setDotenv(Dotenv dotenv) {
        this.dotenv = dotenv;
    }

    public String getProjektVersion() {
        return getProperty("version");
    }

    public String getProjektName() {
        return getProperty("name");
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

    public String getProperty(String key) {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("project.properties"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return properties.getProperty(key);
    }
}