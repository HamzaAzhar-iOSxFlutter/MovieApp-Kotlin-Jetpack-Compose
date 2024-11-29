package com.example.myapplication.screens.home.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.model.Movie
import com.example.myapplication.model.getMovies
import com.example.myapplication.screens.home.MoviewRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, id: String?) {

    val newMovieList = getMovies().filter { movie ->
        movie.id == id
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Details")
                },
                navigationIcon = {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back Arrow",
                        modifier = Modifier
                            .clickable {
                                navController.popBackStack()
                            })
                }
            )
        }
    ) { paddingValues -> // Add the paddingValues parameter
        Surface(
            modifier = Modifier.padding(paddingValues) // Apply padding here
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    MoviewRow(movie = newMovieList.first(), modifier = Modifier.padding(horizontal = 7.dp))

                    Spacer(modifier = Modifier.height(8.dp))
                    Divider()
                    Text("Movie Images")
                    BuildImageRow(movie = newMovieList[0])
                }
            }
        }
    }
}

@Composable
fun BuildImageRow(movie: Movie) {
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .size(240.dp)
                    .padding(12.dp),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = "Movie Poster",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }

        }
    }
}
