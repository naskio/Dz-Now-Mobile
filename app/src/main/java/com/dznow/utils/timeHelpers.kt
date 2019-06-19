package com.dznow.utils

import android.annotation.SuppressLint
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
fun timeSince(time: String): String {
    Locale.setDefault(Locale("fr"))
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val then = sdf.parse(time).time
    val now = System.currentTimeMillis()
    val ago = DateUtils.getRelativeTimeSpanString(then, now, DateUtils.MINUTE_IN_MILLIS)
    return ago.toString()
}
