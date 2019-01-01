package io.github.timtim17.discord.firstkt.modules

import org.javacord.api.event.Event
import org.javacord.api.event.message.MessageCreateEvent

/**
 * @author Austin Jenchi (timtim17)
 */
object BotSayCommand: Module {
    override val cmdName: String = "botsay"
    override val description: String = "Make the bot say something."

    override fun execute(event: Event) {
        val event = event as MessageCreateEvent
        if (!event.messageAuthor.isYourself) {
            val message = event.message.content
            val say = message.substring(message.indexOf(' ') + 1)
            event.channel.sendMessage(say)
        }
    }
}