package com.example.databasesiswa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.databasesiswa.data.AppDatabase
import com.example.databasesiswa.ui.MainScreen
import com.example.databasesiswa.ui.theme.DatabaseSiswaTheme
import com.example.databasesiswa.viewmodel.StudentViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val dao = AppDatabase
            .getDatabase(applicationContext)
            .siswaDao()

        setContent {
            DatabaseSiswaTheme {
                val viewModel = StudentViewModel(dao)
                MainScreen(viewModel)
            }
        }
    }
}
