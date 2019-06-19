package com.dznow.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SourceModel(
    val id: Int,
    val name: String,
    val logo_url: String,
    val background_color: String,
    val text_color: String,
    val website: String,
    val articles: ArrayList<ArticleModel>?
) : Parcelable
