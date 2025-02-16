package net.app.nfusion.moviedatabase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.app.nfusion.moviedatabase.model.Movie
import net.app.nfusion.moviedatabase.repository.MovieRepository

class MovieListViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    init {
        viewModelScope.launch {
            try {
                _movies.value = repository.fetchMovies()
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: ""
            }
            _isLoading.value = false
        }
    }
}
