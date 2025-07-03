package org.mdvsc.rapc.sample

import net.rim.device.api.ui.UiApplication

class SampleApplication: UiApplication() {

    init {
        pushScreen(SampleScreen("Sample Screen"))
    }

}

fun main(args: Array<String>) {
    val app = SampleApplication()
    app.enterEventDispatcher()
}