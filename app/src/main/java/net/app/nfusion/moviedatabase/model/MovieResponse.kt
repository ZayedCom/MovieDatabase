package net.app.nfusion.moviedatabase.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val results: List<Movie>
)
