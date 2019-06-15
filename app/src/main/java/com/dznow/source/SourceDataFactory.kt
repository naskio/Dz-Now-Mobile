package com.dznow.source

import com.dznow.source.SourceModel

object SourceDataFactory {
    fun getSources(count : Int) : List<SourceModel>{
        val items = mutableListOf<SourceModel>()
        for (i in 0..count) {
            val item = SourceModel(
                i,
                "source $i",
                "source $i",
                "source $i",
                "source $i",
                "source $i",
                null
            )
            items.add(item)
        }
        return items
    }
}
