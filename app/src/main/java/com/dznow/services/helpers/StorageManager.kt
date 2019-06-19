package com.dznow.services.helpers

import android.content.Context
import com.dznow.services.bookmarksFileName

fun retrieve(fileName: String) : String {
    return App.appContext?.openFileInput(fileName)?.bufferedReader().use { it?.readText() }.toString()
}

fun store(fileName : String, content : String) {
    App.appContext?.openFileOutput(bookmarksFileName, Context.MODE_PRIVATE).use {
        it?.write(content.toByteArray())
    }
}
