package com.dznow.services.helpers

import android.content.Context

fun retrieve(fileName: String) : String {
    return App.appContext?.openFileInput(fileName)?.bufferedReader().use { it?.readText() }.toString()
}

fun store(fileName : String, content : String) {
    App.appContext?.deleteFile(fileName)
    App.appContext?.openFileOutput(fileName, Context.MODE_PRIVATE).use {
        it?.write(content.toByteArray())
    }
}
