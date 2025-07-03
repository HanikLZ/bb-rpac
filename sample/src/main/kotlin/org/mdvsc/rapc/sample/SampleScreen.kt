package org.mdvsc.rapc.sample

import net.rim.device.api.ui.MenuItem
import net.rim.device.api.ui.component.LabelField
import net.rim.device.api.ui.component.Menu
import net.rim.device.api.ui.container.MainScreen
import net.rim.device.api.util.StringProvider

class SampleScreen(title: String): MainScreen(NO_SYSTEM_MENU_ITEMS or NO_VERTICAL_SCROLL) {

    init {
        setTitle(title)
        val str = listOf("Kotlin", "Java", "Sample")
        val str2 = arrayOf("Java", "Kotlin", "Sample")
        val field = LabelField(str.joinToString())
        val field1 = LabelField(str2.joinToString())
        mainManager.add(field)
        mainManager.add(field1)
    }

    override fun makeMenu(menu: Menu, idx: Int) {
        menu.add(object : MenuItem(StringProvider("Exit"), 0, 1) {
            override fun run() = close()
        })
        super.makeMenu(menu, idx)
    }

}