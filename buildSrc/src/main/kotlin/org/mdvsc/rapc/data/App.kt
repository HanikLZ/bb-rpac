package org.mdvsc.rapc.data

import org.gradle.api.Action
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.Optional

data class App(@get:Input var name: String = "",
               @get:Optional
               @get:Input var distName: String? = null,
               @get:Input var midlet: Boolean = false,
               @get:Input var library: Boolean = false,
               @get:Optional
               @get:Input var version: String? = null,
               @get:Optional
               @get:Input var vendor: String? = null,
               @get:Optional
               @get:Input var description: String? = null,
               @get:Optional
               @get:Input var jarUrl: String? = null,
               @Nested
               val extras: MutableMap<String, String> = mutableMapOf(),
               @Nested val entries: MutableList<AppEntry> = mutableListOf()) {

    fun entry(action: Action<AppEntry>) {
        entries.add(AppEntry().also(action::execute))
    }

    operator fun invoke(action: Action<App>) = action.execute(this)

    operator fun set(name: String, value: String?) {
        if (value == null) extras.remove(name) else extras[name] = value
    }

}
