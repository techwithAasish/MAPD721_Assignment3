package com.aasish.mapd721_assignment3

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aasish.mapd721_assignment3.ui.theme.MAPD721_Assignment3Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment3Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MyApp()

                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MainScreen(onNavigate: (Int) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Main Screen", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))
        Button(onClick = { onNavigate(1) }) {
            Text("Go to Screen 1")
        }
        Button(onClick = { onNavigate(2) }) {
            Text("Go to Screen 2")
        }
        Button(onClick = { onNavigate(3) }) {
            Text("Go to Screen 3")
        }
        Button(onClick = { onNavigate(4) }) {
            Text("Go to Screen 4")
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text("Aasish Mahato", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))
        Text("301373719", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))
    }
}

@ExperimentalAnimationApi
@Composable
fun Screen1(onBackPressed: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = onBackPressed) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color)
        ) {
            Text(
                text = "Color changes in each 2 second",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }
}




@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalAnimationApi
@Composable
fun MyApp() {
    var currentScreen by remember { mutableStateOf(0) }

    val onNavigate: (Int) -> Unit = { screen ->
        currentScreen = screen
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jetpack Compose Animation Demo") },
                navigationIcon = {
                    if (currentScreen != 0) {
                        IconButton(onClick = { currentScreen = 0 }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                }
            )
        }
    ) {

        when (currentScreen) {
            0 -> MainScreen(onNavigate = onNavigate)
            1 -> Screen1(onBackPressed = { currentScreen = 0 })
            2 -> Screen2(onBackPressed = { currentScreen = 0 })
            3 -> Screen3(onBackPressed = { currentScreen = 0 })
            4 -> Screen4(onBackPressed = { currentScreen = 0 })
        }


    }
}


@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MAPD721_Assignment3Theme {
        MyApp()

    }
}