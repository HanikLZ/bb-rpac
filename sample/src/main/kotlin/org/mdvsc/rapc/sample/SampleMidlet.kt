package org.mdvsc.rapc.sample

import javax.microedition.midlet.MIDlet

class SampleMidlet: MIDlet() {
    override fun startApp() {
        val str = listOf("Kotlin", "Java", "Sample")
        val str2 = arrayOf("Java", "Kotlin", "Sample")
        println(str.joinToString())
        println(str2.joinToString())
    }

    override fun pauseApp() {
    }

    override fun destroyApp(p0: Boolean) {
    }
}