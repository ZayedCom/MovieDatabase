package net.app.nfusion.moviedatabase.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.app.nfusion.moviedatabase.data.MovieDatabase
import net.app.nfusion.moviedatabase.remote.ApiService
import net.app.nfusion.moviedatabase.repository.MovieRepository
import net.app.nfusion.moviedatabase.ui.theme.MovieDatabaseTheme
import net.app.nfusion.moviedatabase.viewmodel.MovieDetailViewModel
import net.app.nfusion.moviedatabase.viewmodel.MovieDetailViewModelFactory
import net.app.nfusion.moviedatabase.viewmodel.MovieListViewModel
import net.app.nfusion.moviedatabase.viewmodel.MovieListViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        val db = MovieDatabase.getDatabase(this)
        val repository = MovieRepository(ApiService.movieApi, db.movieDao())
        setContent {
            MovieDatabaseTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "movie_list") {
                    composable("movie_list") {
                        val viewModel = viewModel<MovieListViewModel>(
                            factory = MovieListViewModelFactory(repository)
                        )
                        MovieListScreen(viewModel = viewModel) { selectedMovie ->
                            navController.navigate("movie_detail/${selectedMovie.id}")
                        }
                    }
                    composable("movie_detail/{movieId}") { backStackEntry ->
                        val movieId =
                            backStackEntry.arguments?.getString("movieId")?.toIntOrNull() ?: 0
                        val viewModel = viewModel<MovieDetailViewModel>(
                            factory = MovieDetailViewModelFactory(
                                repository,
                                movieId
                            )
                        )
                        MovieDetailScreen(viewModel = viewModel) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}