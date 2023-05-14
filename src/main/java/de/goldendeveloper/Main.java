package de.goldendeveloper;

import de.goldendeveloper.dcbcore.DCBot;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DCBot dcBot = new DCBot(args, true, true);
        List<CommandData> commandDataList  = new ArrayList<>();
        CommandData data = Commands.slash("echo", "Repeats messages back to you.");
        commandDataList.add(data);
        dcBot.registerCommands(commandDataList);
    }
}