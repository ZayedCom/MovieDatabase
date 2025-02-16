package net.app.nfusion.moviedatabase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.app.nfusion.moviedatabase.repository.MovieRepository

class MovieListViewModelFactory(private val repository: MovieRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return modelClass.cast(MovieListViewModel(repository))
                ?: throw IllegalArgumentException("Unable to cast to the required ViewModel type")
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
