package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    val movies = listOf("Shawshank Redemption", "Batman", "Iron Man", "Frozen", "Taken 1", "Avatar", "Harry Potter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp {
                MainContent(movies = movies)
            }
        }
    }
}

//This is an example of a container function.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(content: @Composable () -> Unit) {
    MyApplicationTheme {
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
               content()
           }
       }
    }
}

@Composable
fun MainContent(movies: List<String>) {
    Column(
        modifier = Modifier
            .padding(14.dp)
    ) {
        LazyColumn {
            items(movies) { movie ->
                MoviewRow(movie) { selectedMovie ->
                    Log.d("Hello", "Movie Tapped!")

                }
            }
        }
    }
}

@Composable
fun MoviewRow(movieName: String, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .height(130.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(movieName)
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
                Icon(Icons.Default.AccountBox, contentDescription = "")
            }
            Text(movieName, modifier = Modifier.padding(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        MyApp {
            MainContent(movies = listOf("Shawshank Redemption", "Batman", "Iron Man", "Frozen", "Taken 1"))
        }
    }
}