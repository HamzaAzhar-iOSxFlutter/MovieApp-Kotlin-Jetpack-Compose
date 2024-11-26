package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp {
                MainContent()
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
           Column(
               modifier = Modifier.padding(paddingValues) // Apply padding here
           ) {
               content()
           }
       }
    }
}

@Composable
fun MainContent() {
    Surface {
        Text("Hello")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        MyApp {
            MainContent()
        }
    }
}