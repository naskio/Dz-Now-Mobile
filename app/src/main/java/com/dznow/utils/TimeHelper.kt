package com.dznow.utils

import android.annotation.SuppressLint
import android.text.format.DateUtils
import java.text.SimpleDateFormat


@SuppressLint("SimpleDateFormat")
fun timeSince(time: String): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val then = sdf.parse(time).time
    val now = System.currentTimeMillis()
    val ago = DateUtils.getRelativeTimeSpanString(then, now, DateUtils.MINUTE_IN_MILLIS)
    return ago.toString()
}
