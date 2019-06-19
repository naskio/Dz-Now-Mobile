package com.dznow.services.storage

import com.dznow.models.SourceModel
import com.dznow.services.helpers.SourceFeed
import com.google.gson.GsonBuilder

class ForYouSources private constructor(){
    private var starringSources : SourceFeed

    init {
        val gson = GsonBuilder().create()
        var fileContent = retrieve(forYouSourcesFileName)
        if (fileContent == "") {
            fileContent = "{ categories: [] }"
        }
        starringSources = gson.fromJson(fileContent, SourceFeed::class.java)
    }

    fun star (source : SourceModel) {
        if (!isStarred(source)) {
            starringSources.sources.add(source)
        }
        saveStarringSources()
    }

    fun unStar (source : SourceModel) {
        if (isStarred(source)) {
            starringSources.sources.remove(starringSources.sources.find { x -> x.id == source.id })
        }
        saveStarringSources()
    }

    fun isStarred (source : SourceModel): Boolean {
        return starringSources.sources.any { x -> x.id == source.id }
    }

    fun saveStarringSources() {
        val gson = GsonBuilder().create()
        val starringSourcesJson = gson.toJson(starringSources, SourceFeed::class.java)
        store(forYouSourcesFileName, starringSourcesJson)
    }

    fun getStarringCategories() : ArrayList<SourceModel> {
        return starringSources.sources
    }

    private object Singleton {
        val instance = ForYouSources()
    }

    companion object {
        fun getInstance() = Singleton.instance
    }
}
