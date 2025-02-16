package net.app.nfusion.moviedatabase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.app.nfusion.moviedatabase.repository.MovieRepository

class MovieDetailViewModelFactory(
    private val repository: MovieRepository,
    private val movieId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return modelClass.cast(MovieDetailViewModel(repository, movieId))
                ?: throw IllegalArgumentException("Unable to cast to the required ViewModel type")
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
