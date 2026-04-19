package com.example.helloandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloandroid.ui.theme.HelloAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloAndroidTheme {
                // Surface untuk background aplikasi
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Kalkulatorapp()
                }
            }
        }
    }
}

@Composable
fun Kalkulatorapp() {
    // 3. State Variable (Menyimpan input user)
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var operator by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    // 4. Layout Column (Menyusun ke bawah)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding(), // Agar tidak tertutup status bar
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Kalkulator Sederhana", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        // 5. Input Angka Pertama
        OutlinedTextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Angka pertama") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 6. Input Operator
        OutlinedTextField(
            value = operator,
            onValueChange = { operator = it },
            label = { Text("Operator (+, -, *, /)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 7. Input Angka Kedua
        OutlinedTextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Angka kedua") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 8. Tombol Hitung
        Button(
            onClick = {
                // 9. Konversi Input ke Angka (Aman dari crash)
                val n1 = num1.toDoubleOrNull()
                val n2 = num2.toDoubleOrNull()

                // 10. Validasi & Logika Hitung
                if (n1 != null && n2 != null) {
                    result = when (operator) {
                        "+" -> (n1 + n2).toString()
                        "-" -> (n1 - n2).toString()
                        "*" -> (n1 * n2).toString()
                        "/" -> if (n2 != 0.0) (n1 / n2).toString() else "Tidak bisa dibagi 0"
                        else -> "Operator Salah!"
                    }
                } else {
                    result = "Input harus angka!"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hitung")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Menampilkan Hasil
        Text(
            text = "Hasil: $result",
            fontSize = 20.sp,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}