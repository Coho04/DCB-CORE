package io.github.coho04.dcbcore;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConfigTest {

    private Config config;
    private Dotenv dotenvMock;

    @BeforeEach
    void setUp() {
        dotenvMock = mock(Dotenv.class);
        config = new Config();
        config.setDotenv(dotenvMock);
    }
//
//    @Test
//    void getProjektVersion_returnsCorrectVersion() throws IOException {
//        Properties properties = new Properties();
//        properties.setProperty("version", "1.0.0");
//        InputStream inputStream = mock(InputStream.class);
//        when(inputStream.read(any(byte[].class), anyInt(), anyInt())).thenReturn(-1);
//        when(config.getClass().getClassLoader().getResourceAsStream("project.properties")).thenReturn(inputStream);
//        properties.load(inputStream);
//
//        assertEquals("1.0.0", config.getProjektVersion());
//    }

//    @Test
//    void getProjektName_returnsCorrectName() throws IOException {
//        Properties properties = new Properties();
//        properties.setProperty("name", "MyProject");
//        InputStream inputStream = mock(InputStream.class);
//        when(inputStream.read(any(byte[].class), anyInt(), anyInt())).thenReturn(-1);
//        when(config.getClass().getClassLoader().getResourceAsStream("project.properties")).thenReturn(inputStream);
//        properties.load(inputStream);
//
//        assertEquals("MyProject", config.getProjektName());
//    }

    @Test
    void getSentryDNS_returnsCorrectValue() {
        when(dotenvMock.get("SENTRY_DNS")).thenReturn("sentry_dns_value");
        assertEquals("sentry_dns_value", config.getSentryDNS());
    }

    @Test
    void getDiscordWebhook_returnsCorrectValue() {
        when(dotenvMock.get("DISCORD_WEBHOOK")).thenReturn("discord_webhook_value");
        assertEquals("discord_webhook_value", config.getDiscordWebhook());
    }

    @Test
    void getDiscordToken_returnsCorrectValue() {
        when(dotenvMock.get("DISCORD_TOKEN")).thenReturn("discord_token_value");
        assertEquals("discord_token_value", config.getDiscordToken());
    }

//    @Test
//    void getProperty_returnsNullForNonExistentKey() throws IOException {
//        Properties properties = new Properties();
//        InputStream inputStream = mock(InputStream.class);
//        when(inputStream.read(any(byte[].class), anyInt(), anyInt())).thenReturn(-1);
//        when(config.getClass().getClassLoader().getResourceAsStream("project.properties")).thenReturn(inputStream);
//        properties.load(inputStream);
//
//        assertNull(config.getProperty("non_existent_key"));
//    }
}