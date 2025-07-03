package org.mdvsc.rapc.data

import org.gradle.api.Action
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.Optional

data class AppEntry(@get:Optional
                    @get:Input var title: String? = null,
                    @get:Input var systemModule: Boolean = false,
                    @get:Input var runOnStartup: Boolean = false,
                    @get:Input var startupTier: Int = 7,
                    @get:Optional
                    @get:Input var ribbonPosition: Int? = null,
                    @get:Optional
                    @get:Input var nameResourceBundle: String? = null,
                    @get:Optional
                    @get:Input var nameResourceId: Int? = null,
                    @get:Optional
                    @get:Input var arguments: String? = null,
                    @Nested val icons: MutableList<AppIcon> = mutableListOf()) {

    fun icon(action: Action<AppIcon>) {
        icons.add(AppIcon().also(action::execute))
    }

}