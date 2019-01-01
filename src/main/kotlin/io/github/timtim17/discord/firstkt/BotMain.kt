package io.github.timtim17.discord.firstkt

import io.github.timtim17.discord.firstkt.modules.Module
import org.javacord.api.DiscordApi
import org.javacord.api.DiscordApiBuilder
import org.javacord.api.entity.activity.ActivityType
import org.reflections.Reflections
import java.util.stream.Collectors.toSet

/**
 * @author Austin Jenchi (timtim17)
 */
object BotMain {
    private val DISCORD_TOKEN_FILE = "discord_token.secret"
    private val TOKEN_ENV_VAR = "DISCORD_TOKEN"
    private val TOKEN: String = BotMain::class.java.getResource("/" + this.DISCORD_TOKEN_FILE)?.readText()
        ?: System.getenv(TOKEN_ENV_VAR)   // if neither of these exist it immediately crashes

    val PREFIX = "|"
    val api: DiscordApi = DiscordApiBuilder().setToken(TOKEN).login().join()
    val modules: MutableSet<Module>? =
        Reflections(javaClass.packageName + ".modules").getSubTypesOf(Module::class.java)
            .stream()
            .map { it.getField("INSTANCE").get(null) as Module }
            .collect(toSet())

    @JvmStatic
    fun main(args: Array<String>) {
        println("Hello world!")
        println("Starting Discord bot.")

        println("Discord API object created!")

        api.addMessageCreateListener { event ->
            val content = event.message.content
            if (event.messageAuthor != api.yourself && content.substring(0, PREFIX.length) == PREFIX) {
                val endIndex = content.indexOf(' ')
                val command = if (endIndex == -1) content.substring(PREFIX.length)
                    else content.substring(PREFIX.length, endIndex)
                var resolved = false
                for (it in modules!!) {
                    if (command == it.cmdName) {
                        it.execute(event)
                        resolved = true
                        break
                    }
                }
                if (!resolved) {
                    event.channel.sendMessage("❌ Invalid command `${PREFIX + command}`!")
                }
            }
        }

        println("Added Message Create Listener")

        api.updateActivity(ActivityType.WATCHING, "the chat // ${PREFIX}help")

        println("You can invite the bot by using the following url: " + api.createBotInvite())
    }
}
