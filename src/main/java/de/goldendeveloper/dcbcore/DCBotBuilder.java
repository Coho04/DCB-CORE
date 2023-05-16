package de.goldendeveloper.dcbcore;

import de.goldendeveloper.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Collections;
import java.util.LinkedList;

public class DCBotBuilder {

    private final LinkedList<ListenerAdapter> events = new LinkedList<>();
    private LinkedList<CommandInterface> commandDataList = new LinkedList<>();
    private final Boolean withServerCommunicator;
    private final String[] args;

    @SuppressWarnings("unused")
    public DCBotBuilder(String[] args, boolean withServerCommunicator) {
        this.args = args;
        this.withServerCommunicator = withServerCommunicator;
    }

    public DCBotBuilder(String[] args) {
        this.args = args;
        this.withServerCommunicator = false;
    }

    public void registerCommands(LinkedList<CommandInterface> commandDataList) {
        this.commandDataList = commandDataList;
    }

    @SuppressWarnings("unused")
    public void registerEvents(ListenerAdapter... listenerAdapters) {
        Collections.addAll(events, listenerAdapters);
    }

    public DCBot build() {
        return new DCBot(this.args, this.withServerCommunicator, events, commandDataList);
    }
}
