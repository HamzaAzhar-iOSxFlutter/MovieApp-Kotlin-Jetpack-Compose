package com.example.myapplication.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.model.Movie
import com.example.myapplication.model.getMovies
import com.example.myapplication.navigation.MoviesScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val movies = getMovies()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Movies")
                }
            )
        }
    ) { paddingValues -> // Add the paddingValues parameter
        Surface(
            modifier = Modifier.padding(paddingValues) // Apply padding here
        ) {
            MainContent(navController = navController)
        }
    }
}

@Composable
fun MainContent(navController: NavController, movies: List<Movie> = getMovies()) {
    Column(
        modifier = Modifier
            .padding(14.dp)
    ) {
        LazyColumn {
            items(movies) { movie ->
                MoviewRow(movie) { name ->
                    navController.navigate(MoviesScreen.DetailsScreen.name+"/$name")

                }
            }
        }
    }
}

@Preview
@Composable
fun MoviewRow(movie: Movie = getMovies()[0], onItemClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .height(130.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shadowElevation = 4.dp,
            ) {
                AsyncImage(
                    model = movie.images[0],
                    contentDescription = "Movie Poster",
                )


            }

            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(movie.title, style = MaterialTheme.typography.titleMedium)
                Text(movie.director, style = MaterialTheme.typography.titleSmall)
                Text(movie.year, style = MaterialTheme.typography.titleSmall)
            }
        }
    }
}