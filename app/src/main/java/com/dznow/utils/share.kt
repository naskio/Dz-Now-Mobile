package com.dznow.utils

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat

fun shareAction(context: Context, title: String?, subject: String?, text: String?) {
    val i = Intent(Intent.ACTION_SEND).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    i.type = "text/plain"
    i.putExtra(Intent.EXTRA_SUBJECT, subject)
    i.putExtra(Intent.EXTRA_TEXT, text)
    ContextCompat.startActivity(context, Intent.createChooser(i, title), null)
}
