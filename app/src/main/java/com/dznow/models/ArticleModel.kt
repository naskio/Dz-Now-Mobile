package com.dznow.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleModel(
    val id: Int,
    val title: String,
    val content: String,
    val minutes_read: Int,
    val cover_url: String?,
    val created_at: String,
    val category: CategoryModel?,
    val source: SourceModel?,
    val url: String?,
    val videos: ArrayList<VideoModel>?
) : Parcelable
