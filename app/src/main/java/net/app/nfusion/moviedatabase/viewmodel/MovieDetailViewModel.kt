package net.app.nfusion.moviedatabase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.app.nfusion.moviedatabase.model.Movie
import net.app.nfusion.moviedatabase.repository.MovieRepository

class MovieDetailViewModel(private val repository: MovieRepository, movieId: Int) : ViewModel() {
    private val _movie = MutableStateFlow<Movie?>(null)
    val movie: StateFlow<Movie?> = _movie

    init {
        viewModelScope.launch {
            _movie.value = repository.getMovieById(movieId)
        }
    }
}
