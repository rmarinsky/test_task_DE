package com.ravlyk

import com.codeborne.selenide.Configuration

open class BaseTest {

    companion object {
        init {
            Configuration.baseUrl = "https://www.douglas.de/de"
            Configuration.browserSize = "1920x1080"
        }
    }

}