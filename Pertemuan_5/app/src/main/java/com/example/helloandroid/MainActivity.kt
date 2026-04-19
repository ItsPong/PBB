package com.example.helloandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Dua baris ini wajib ada agar 'by remember' tidak error
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // GUNAKAN NAMA TEMA YANG BENAR: HelloAndroidTheme
            HelloAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceRollerWithImageAndButton(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
        }
    }
}

// --- FUNGSI DICE ROLLER ---
@Composable
fun DiceRollerWithImageAndButton(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }

    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = result.toString()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result = (1..6).random() }) {
            Text(text = "Roll")
        }
    }
}

// --- PREVIEW UNTUK DICE ROLLER ---
@Preview(showBackground = true)
@Composable
fun DiceRollerPreview() {
    HelloAndroidTheme {
        DiceRollerWithImageAndButton(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}

// Fungsi Greeting & GreetingText tetap di bawah sini tidak masalah