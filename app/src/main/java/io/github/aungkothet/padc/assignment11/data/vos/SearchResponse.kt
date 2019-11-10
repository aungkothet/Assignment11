package io.github.aungkothet.padc.assignment11.data.vos

data class SearchResponse(
    val total: Int,
    val total_pages: Int,
    val results: List<PhotoVO>
)
