package org.mdvsc.rapc.data

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional

data class AppIcon(@get:Input var normal: String = "",
                   @get:Optional
                   @get:Input var focus: String? = null)
