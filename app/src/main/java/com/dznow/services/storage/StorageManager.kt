package com.dznow.services.storage

import android.content.Context
import com.dznow.services.helpers.App

fun retrieve(fileName: String) : String {
    return App.appContext?.openFileInput(fileName)?.bufferedReader().use { it?.readText() }.toString()
}

fun store(fileName : String, content : String) {
    App.appContext?.deleteFile(fileName)
    App.appContext?.openFileOutput(fileName, Context.MODE_PRIVATE).use {
        it?.write(content.toByteArray())
    }
}
