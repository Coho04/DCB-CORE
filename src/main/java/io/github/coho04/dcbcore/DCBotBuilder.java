package io.github.coho04.dcbcore;

import io.github.coho04.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.Collections;
import java.util.LinkedList;

@SuppressWarnings("unused")
public class DCBotBuilder {

    private final LinkedList<ListenerAdapter> events = new LinkedList<>();
    private final LinkedList<CommandInterface> commandDataList = new LinkedList<>();
    private final LinkedList<CommandInterface> removedCommandDataList = new LinkedList<>();
    private final LinkedList<GatewayIntent> gatewayIntentList = new LinkedList<>();
    private final Boolean withServerCommunicator;
    private final String[] args;

    public DCBotBuilder(String[] args, boolean withServerCommunicator) {
        this.args = args;
        this.withServerCommunicator = withServerCommunicator;
    }

    public DCBotBuilder(String[] args) {
        this.args = args;
        this.withServerCommunicator = false;
    }

    public void registerGatewayIntents(GatewayIntent... gatewayIntents) {
        if (gatewayIntents != null) {
            Collections.addAll(gatewayIntentList, gatewayIntents);
        }
    }

    public void registerCommands(CommandInterface... commands) {
        if (commands != null) {
            Collections.addAll(commandDataList, commands);
        }
    }

    public void removeCommands(CommandInterface... commands) {
        if (commands != null) {
            Collections.addAll(removedCommandDataList, commands);
        }
    }

    public void registerEvents(ListenerAdapter... listenerAdapters) {
        if (listenerAdapters != null){
            Collections.addAll(events, listenerAdapters);
        }
    }

    public DCBot build() {
        return new DCBot(this.args, this.withServerCommunicator, events, commandDataList, gatewayIntentList, removedCommandDataList);
    }
}
