package com.example.decisionmaker

import android.R
import android.R.attr.button
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.decisionmaker.ui.theme.DecisionMakerTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DecisionMakerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DecisionScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DecisionMakerTheme {
        Greeting("Android")
    }
}

@Composable
fun DecisionScreen(
    modifier: Modifier = Modifier
) {
    // temp variable to store result and count of clicks
    var outputText by remember { mutableStateOf("") }
    var clickCounter by remember {mutableStateOf(0)}

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // output text
        Text(
            text = outputText,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )

        //buttons
        Row(modifier = Modifier) {
            Button(
                onClick = {
                    clickCounter++
                    outputText = probabilityFinder(0.5)
                }
            ) {
                Text(
                    text = "1/2"
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            Button(
                onClick = {
                    clickCounter++
                    outputText = probabilityFinder(0.25)
                }
            ) {
                Text(
                    text = "1/4"
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            Button(
                onClick = {
                    clickCounter++
                    outputText = probabilityFinder(0.1)
                }
            ) {
                Text(
                    text = "1/10"
                )
            }
        }

        //click counter
        Text(
            text = "$clickCounter",
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        //credentials
        Text(
            text = "ccid: hridey",
            fontSize = 12.sp,
            modifier = Modifier
        )
        Text(
            text = "student number: 1833471",
            fontSize = 12.sp,
            modifier = Modifier
        )
    }
}

fun probabilityFinder (buttonProbability: Double): String {
    val randomNumber = Math.random()
    val answer: String =
        // 25% change of 0.0 to 1.0 nubmer being under 0.25 so it wokrs
        if (randomNumber < buttonProbability) "Yes"
        else "No"
    return answer
}