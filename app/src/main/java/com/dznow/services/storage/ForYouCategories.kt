package com.dznow.services.storage

import com.dznow.models.CategoryModel
import com.dznow.services.helpers.CategoryFeed
import com.google.gson.GsonBuilder

class ForYouCategories private constructor(){
    private var starringCategories : CategoryFeed

    init {
        val gson = GsonBuilder().create()
        var fileContent = retrieve(forYouCategoriesFileName)
        if (fileContent == "") {
            fileContent = "{ categories: [] }"
        }
        starringCategories = gson.fromJson(fileContent, CategoryFeed::class.java)
    }

    fun star (category : CategoryModel) {
        if (!isStarred(category)) {
            starringCategories.categories.add(category)
        }
        saveStarringCategories()
    }

    fun unStar (category : CategoryModel) {
        if (isStarred(category)) {
            starringCategories.categories.remove(starringCategories.categories.find { x -> x.id == category.id })
        }
        saveStarringCategories()
    }

    fun isStarred (category : CategoryModel): Boolean {
        return starringCategories.categories.any { x -> x.id == category.id }
    }

    fun saveStarringCategories() {
        val gson = GsonBuilder().create()
        val starringCategoriesJson = gson.toJson(starringCategories, CategoryFeed::class.java)
        store(forYouCategoriesFileName, starringCategoriesJson)
    }

    fun getStarringCategories() : ArrayList<CategoryModel> {
        return starringCategories.categories
    }

    private object Singleton {
        val instance = ForYouCategories()
    }

    companion object {
        fun getInstance() = Singleton.instance
    }
}
