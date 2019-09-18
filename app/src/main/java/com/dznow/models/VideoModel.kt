package com.dznow.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoModel(
    val title: String,
    val cover: String?,
    val url: String?
) : Parcelable