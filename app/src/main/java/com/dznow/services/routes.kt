package com.dznow.services


const val BASE = "https://dznow.herokuapp.com/api/v0"

val homeAPI: (String) -> String = { l -> "$BASE/$l/all" }

val sourceAPI: (String, Int, Int) -> String = { l, s, p ->
    "$BASE/$l/source/$s/$p"
}

val categoryAPI: (String, Int, Int) -> String = { l, s, p ->
    "$BASE/$l/category/$s/$p"
}

val sourcesAPI: (String) -> String = { l ->
    "$BASE/$l/sources"
}

val categoriesAPI: (String) -> String = { l ->
    "$BASE/$l/categories"
}

val readingAPI: (String, Int, Int) -> String = { l, m, p ->
    "$BASE/$l/reading_time/$m/$p"
}