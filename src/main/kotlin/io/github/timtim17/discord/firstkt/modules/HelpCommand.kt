package io.github.timtim17.discord.firstkt.modules

import io.github.timtim17.discord.firstkt.BotMain
import org.javacord.api.entity.message.embed.EmbedBuilder
import org.javacord.api.event.Event
import org.javacord.api.event.message.MessageCreateEvent
import java.awt.Color

/**
 * @author Austin Jenchi (timtim17)
 */
object HelpCommand: Module {
    override val cmdName: String = "help"
    override val description: String = "Lists all available commands and how to use them."

    override fun execute(event: Event) {
        val builder = EmbedBuilder()
        builder.setTitle("Bot Help")
        builder.setAuthor(event.api.yourself)
        builder.setFooter("Requested by ${(event as MessageCreateEvent).messageAuthor.discriminatedName}")
        builder.setColor(Color(114, 137, 218))
        BotMain.modules!!.forEach {
            builder.addField(BotMain.PREFIX + it.cmdName, it.description)
        }
        event.channel.sendMessage(builder)
    }
}