package net.app.nfusion.moviedatabase.repository

import net.app.nfusion.moviedatabase.data.MovieDao
import net.app.nfusion.moviedatabase.model.Movie
import net.app.nfusion.moviedatabase.remote.MovieApi

class MovieRepository(
    private val api: MovieApi,
    private val dao: MovieDao
) {
    suspend fun fetchMovies(): List<Movie> {
        return try {
            val response = api.getMovies()
            dao.insertMovies(response.results)
            response.results
        } catch (e: Exception) {
            // If API call fails (e.g., no internet), load cached movies.
            dao.getMovies()
        }
    }

    suspend fun getMovieById(id: Int): Movie? = dao.getMovieById(id)
}
