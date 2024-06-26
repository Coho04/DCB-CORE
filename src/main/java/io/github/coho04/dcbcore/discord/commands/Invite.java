package io.github.coho04.dcbcore.discord.commands;

import io.github.coho04.dcbcore.DCBot;
import io.github.coho04.dcbcore.interfaces.CommandInterface;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class Invite implements CommandInterface {

    @Override
    public CommandData commandData() {
        return Commands.slash("invite", "Lade den Bot auf deinen Server ein!");
    }

    @Override
    public void runSlashCommand(SlashCommandInteractionEvent e, DCBot dcBot) {
        if (e == null || dcBot == null) {
            return;
        }

        e.getInteraction().reply("Mit dem Button kannst du mich auf deinen Server einladen!")
                .addActionRow(
                        Button.link(e.getJDA().setRequiredScopes("applications.commands")
                                .getInviteUrl(Permission.ADMINISTRATOR), "Hier Klicken")
                ).queue();

    }
}