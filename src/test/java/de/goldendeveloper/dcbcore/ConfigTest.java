package de.goldendeveloper.dcbcore;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import io.github.cdimascio.dotenv.Dotenv;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

class ConfigTest {

    @Mock
    private Dotenv mockedDotenv;

    private Config config;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        config = new Config();
        config.dotenv = mockedDotenv;
    }

    @Test
    void testGetProjektVersion() {
        Properties properties = new Properties();
        properties.setProperty("version", "1.0.0");
        Config spyConfig = Mockito.spy(new Config());
        String propertiesString = "version=1.0.0";
        InputStream inputStream = new ByteArrayInputStream(propertiesString.getBytes(StandardCharsets.UTF_8));
        Mockito.when(spyConfig.loadProperties(inputStream)).thenReturn(properties);
        String expected = "${project.version}";
        String actual = spyConfig.getProjektVersion();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetProjektName() {
        Properties properties = new Properties();
        properties.setProperty("name", "MyProject");
        Config spyConfig = Mockito.spy(new Config());
        String propertiesString = "name=MyProject";
        InputStream inputStream = new ByteArrayInputStream(propertiesString.getBytes(StandardCharsets.UTF_8));
        Mockito.when(spyConfig.loadProperties(inputStream)).thenReturn(properties);
        String expected = "${project.artifactId}";
        String actual = spyConfig.getProjektName();
        assertEquals(expected, actual);
    }

    @Test
    void testGetSentryDNS() {
        Mockito.when(mockedDotenv.get("SENTRY_DNS")).thenReturn("my-sentry-dns");
        config = new Config();
        config.setDotenv(mockedDotenv);
        String sentryDNS = config.getSentryDNS();
        assertEquals("my-sentry-dns", sentryDNS);
    }

    @Test
    void testGetDiscordWebhook() {
        Mockito.when(mockedDotenv.get("DISCORD_WEBHOOK")).thenReturn("my-discord-webhook");
        config = new Config();
        config.setDotenv(mockedDotenv);
        String discordWebhook = config.getDiscordWebhook();
        assertEquals("my-discord-webhook", discordWebhook);
    }

    @Test
    void testGetDiscordToken() {
        Mockito.when(mockedDotenv.get("DISCORD_TOKEN")).thenReturn("my-discord-token");
        config = new Config();
        config.setDotenv(mockedDotenv);
        String discordToken = config.getDiscordToken();
        assertEquals("my-discord-token", discordToken);
    }

    @Test
    void testGetServerPort() {
        Mockito.when(mockedDotenv.get("SERVER_PORT")).thenReturn("8080");
        config = new Config();
        config.setDotenv(mockedDotenv);
        int serverPort = config.getServerPort();
        assertEquals(8080, serverPort);
    }

    @Test
    void testGetServerHostname() {
        Mockito.when(mockedDotenv.get("SERVER_HOSTNAME")).thenReturn("localhost");
        config = new Config();
        config.setDotenv(mockedDotenv);
        String serverHostname = config.getServerHostname();
        assertEquals("localhost", serverHostname);
    }
}