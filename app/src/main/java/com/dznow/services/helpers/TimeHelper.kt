package com.dznow.services.helpers

import android.annotation.SuppressLint
import android.text.format.DateUtils
import java.text.SimpleDateFormat

class TimeHelper {
    @SuppressLint("SimpleDateFormat")
    fun getElapsedTime (time : String) : String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val then = sdf.parse(time).time
        val now = System.currentTimeMillis()
        val ago = DateUtils.getRelativeTimeSpanString(then, now, DateUtils.MINUTE_IN_MILLIS)
        return ago.toString()
    }
}
