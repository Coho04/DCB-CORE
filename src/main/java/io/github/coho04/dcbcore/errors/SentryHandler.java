package io.github.coho04.dcbcore.errors;

import io.github.coho04.dcbcore.DCBot;
import io.sentry.Sentry;

public class SentryHandler {

    public SentryHandler(String dns, DCBot dcBot) {
        Sentry.init(options -> {
            options.setDsn(dns);
            options.setTracesSampleRate(1.0);
            options.setEnvironment(dcBot.getDeployment() ? "Production" : "localhost");
        });
    }
}
