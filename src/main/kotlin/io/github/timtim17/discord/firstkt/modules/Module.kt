package io.github.timtim17.discord.firstkt.modules

import org.javacord.api.event.Event

/**
 * @author Austin Jenchi (timtim17)
 */
interface Module {
    fun execute(event: Event)
    val cmdName: String
    val description: String
}
