package io.github.timtim17.discord.firstkt

import org.javacord.api.DiscordApi
import org.javacord.api.DiscordApiBuilder
import org.javacord.api.entity.message.Message
import java.time.Instant
import java.util.*
import java.util.concurrent.CompletableFuture

/**
 * @author Austin Jenchi (timtim17)
 */
object BotMain {
    private val DISCORD_TOKEN_FILE = "discord_token.secret"
    private val TOKEN_ENV_VAR = "DISCORD_TOKEN"
    private val token: String = BotMain::class.java.getResource("/" + this.DISCORD_TOKEN_FILE)?.readText()
        ?: System.getenv(TOKEN_ENV_VAR)   // if neither of these exist it immediately crashes

    val api: DiscordApi = DiscordApiBuilder().setToken(token).login().join()

    @JvmStatic
    fun main(args: Array<String>) {
        println("Hello world!")
        println("Starting Discord bot.")

        println("Discord API object created!")

        api.addMessageCreateListener { event ->
            if (event.message.content.equals("!ping", ignoreCase = true)) {
                event.channel.sendMessage("Pong!")
            }
        }

        println("Added Message Create Listener")

        println("You can invite the bot by using the following url: " + api.createBotInvite())
    }
}
