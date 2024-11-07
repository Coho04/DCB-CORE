package io.github.coho04.dcbcore.discord.events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The ButtonClickListener class extends ListenerAdapter to handle button interactions.
 * It manages pagination of message embeds using "prev" and "next" buttons.
 */
public class ButtonClickListener extends ListenerAdapter {
    private final Message message;
    private final List<MessageEmbed> pages;
    private final AtomicInteger currentPage;
    private final Button prev;
    private final Button next;

    /**
     * Constructs a ButtonClickListener with the specified message, pages, current page, and buttons.
     *
     * @param message     The message containing the buttons.
     * @param pages       The list of message embeds representing the pages.
     * @param currentPage The current page index.
     * @param prev        The button to navigate to the previous page.
     * @param next        The button to navigate to the next page.
     */
    public ButtonClickListener(Message message, List<MessageEmbed> pages, AtomicInteger currentPage, Button prev, Button next) {
        this.message = message;
        this.pages = pages;
        this.currentPage = currentPage;
        this.prev = prev;
        this.next = next;
    }

    /**
     * Handles button interactions to navigate through the pages.
     *
     * @param event The ButtonInteractionEvent triggered by a button click.
     */
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        var buttonId = event.getButton().getId();
        if (!buttonId.equalsIgnoreCase("core-prev") && !buttonId.equalsIgnoreCase("core-next")) {
            System.out.println("Button not found");
            return;
        }
        if (!event.getMessageId().equals(message.getId())) return;

        int newPage = currentPage.get();
        if (buttonId.equals("core-prev")) {
            newPage = Math.max(0, currentPage.decrementAndGet());
        } else if (buttonId.equals("core-next")) {
            newPage = Math.min(pages.size() - 1, currentPage.incrementAndGet());
        }
        event.editMessageEmbeds(pages.get(newPage))
                .setActionRow(
                        prev.withDisabled(newPage == 0),
                        next.withDisabled(newPage == pages.size() - 1)
                ).queue();
    }
}
