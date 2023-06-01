package de.goldendeveloper.dcbcore;

import de.goldendeveloper.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Collections;
import java.util.LinkedList;

public class DCBotBuilder {

    private final LinkedList<ListenerAdapter> events = new LinkedList<>();
    private final LinkedList<CommandInterface> commandDataList = new LinkedList<>();
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

    public void registerCommands(CommandInterface... commands) {
        Collections.addAll(commandDataList, commands);
    }

    public void registerEvents(ListenerAdapter... listenerAdapters) {
        Collections.addAll(events, listenerAdapters);
    }

    public DCBot build() {
        return new DCBot(this.args, this.withServerCommunicator, events, commandDataList);
    }
}
