package io.github.coho04.dcbcore.discord.commands;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestartTest {

    @Test
    public void shouldReturnCorrectCommandData() {
        Restart restart = new Restart();
        CommandData commandData = restart.commandData();

        assertEquals("restart", commandData.getName());
//        assertEquals("Starte den Bot neu!", commandData.getDescription());
    }
}