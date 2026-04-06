package com.example.helloandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloandroid.ui.theme.HelloAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // 1. Panggil Tema (Pastikan namanya sama dengan yang ada di folder ui/theme)
            HelloAndroidTheme {
                // 2. Surface sebagai Container UI
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 3. Memanggil Composable GreetingText
                    GreetingText(
                        message = "Happy Birthday Sam!",
                        from = "From Emma"
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
    HelloAndroidTheme {
        Greeting("Android")
    }
}

@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    // 5. Menggunakan Column agar teks tersusun vertikal (atas-bawah)
    Column(
        verticalArrangement = Arrangement.Center, // Membuat konten di tengah layar secara vertikal
        modifier = modifier.padding(8.dp) // Memberi jarak dari pinggir
    ) {
        // 6. Menampilkan Teks Ucapan (Besar)
        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center
        )
        // 7. Menampilkan Nama Pengirim (Lebih Kecil)
        Text(
            text = from,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.End) // Menggeser teks ke kanan
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    HelloAndroidTheme {
        GreetingText(message = "Happy Birthday Sam!", from = "From Emma")
    }
}
