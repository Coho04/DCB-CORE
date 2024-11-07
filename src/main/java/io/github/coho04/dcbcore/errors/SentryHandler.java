package io.github.coho04.dcbcore.errors;

import io.github.coho04.dcbcore.DCBot;
import io.sentry.Sentry;

/**
 * The SentryHandler class initializes the Sentry error tracking system.
 */
public class SentryHandler {

    /**
     * Constructs a new SentryHandler instance.
     * Initializes Sentry with the provided DSN and environment settings.
     *
     * @param dns   The Data Source Name (DSN) for Sentry.
     * @param dcBot The DCBot instance to determine the environment.
     */
    public SentryHandler(String dns, DCBot dcBot) {
        Sentry.init(options -> {
            options.setDsn(dns);
            options.setTracesSampleRate(1.0);
            options.setEnvironment(dcBot.getDeployment() ? "Production" : "localhost");
        });
    }
}
