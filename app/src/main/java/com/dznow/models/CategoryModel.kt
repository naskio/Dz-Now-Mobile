package com.dznow.models

import android.os.Parcelable
import com.dznow.models.ArticleModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryModel(
    val id: Int,
    val name: String,
    val background_url: String,
    val background_color: String,
    val text_color: String,
    val articles: ArrayList<ArticleModel>?
) : Parcelable