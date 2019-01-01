package io.github.timtim17.discord.firstkt.modules

import org.javacord.api.event.Event
import org.javacord.api.event.message.MessageCreateEvent

/**
 * @author Austin Jenchi (timtim17)
 */
object PingPongCommand: Module {
    override val cmdName: String = "ping"
    override val description: String = "Responds to `ping`s."

    override fun execute(event: Event) {
        (event as MessageCreateEvent).channel.sendMessage("Pong!")
    }
}
