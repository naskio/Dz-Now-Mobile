package com.dznow.services.storage

import android.content.Context
import com.dznow.services.helpers.App
import java.io.File

fun retrieve(fileName: String): String {
    if (!fileExists(fileName)) {
        return "";
    }
    return App.appContext?.openFileInput(fileName)?.bufferedReader().use { it?.readText() }.toString()
}

fun store(fileName: String, content: String) {
    App.appContext?.deleteFile(fileName)
    App.appContext?.openFileOutput(fileName, Context.MODE_PRIVATE).use {
        it?.write(content.toByteArray())
    }
}

fun fileExists(fileName: String): Boolean {
    val file = App.appContext?.getFileStreamPath(fileName);
    if (file == null || !file.exists()) {
        return false;
    }
    return true;
}
