package de.goldendeveloper.dcbcore;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import io.github.cdimascio.dotenv.Dotenv;
import org.mockito.MockitoAnnotations;

class ConfigTest {

    @Mock
    private Dotenv dotenv;

    private Config config;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        config = new Config();
        config.setDotenv(dotenv);
    }

    @Test
    void getSentryDNS_returnsExpectedValue() {
        when(dotenv.get("SENTRY_DNS")).thenReturn("sentry_dns");
        assertEquals("sentry_dns", config.getSentryDNS());
    }

    @Test
    void getDiscordWebhook_returnsExpectedValue() {
        when(dotenv.get("DISCORD_WEBHOOK")).thenReturn("discord_webhook");
        assertEquals("discord_webhook", config.getDiscordWebhook());
    }

    @Test
    void getDiscordToken_returnsExpectedValue() {
        when(dotenv.get("DISCORD_TOKEN")).thenReturn("discord_token");
        assertEquals("discord_token", config.getDiscordToken());
    }

    @Test
    void getServerPort_returnsExpectedValue() {
        when(dotenv.get("SERVER_PORT")).thenReturn("8080");
        assertEquals(8080, config.getServerPort());
    }

    @Test
    void getServerHostname_returnsExpectedValue() {
        when(dotenv.get("SERVER_HOSTNAME")).thenReturn("localhost");
        assertEquals("localhost", config.getServerHostname());
    }

    @Test
    void getProperty_returnsNullWhenKeyDoesNotExist() {
        when(dotenv.get("non_existent_key")).thenReturn(null);
        assertNull(config.getProperty("non_existent_key"));
    }
}