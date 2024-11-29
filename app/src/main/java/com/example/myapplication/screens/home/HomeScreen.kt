package com.example.myapplication.screens.home

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun MoviewRow(movie: Movie = getMovies()[0], modifier: Modifier = Modifier,onItemClick: (String) -> Unit = {}) {
    var expandedState by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
            .padding(4.dp)
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

                    AnimatedVisibility(expandedState) {
                        Column {
                            Text(
                                buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontSize = 13.sp
                                        )
                                    ) {
                                        append("Plot: ")
                                    }

                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.DarkGray,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Light
                                        )
                                    ) {
                                        append(movie.plot)
                                    }
                                }, modifier = Modifier.padding(6.dp)
                            )
                            Divider()
                            Text("Director: ${movie.director}", style = MaterialTheme.typography.labelSmall)
                            Text("Actors: ${movie.actors}", style = MaterialTheme.typography.labelSmall)
                            Text("Rating: ${movie.rating}", style = MaterialTheme.typography.labelSmall)
                        }
                    }


                Icon(
                    imageVector = if (expandedState) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Arrow down image",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(25.dp)
                        .clickable {
                            expandedState = !expandedState
                        })

            }
        }
    }
}