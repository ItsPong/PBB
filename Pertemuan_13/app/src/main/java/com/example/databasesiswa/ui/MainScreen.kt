package com.example.databasesiswa.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.databasesiswa.data.Siswa
import com.example.databasesiswa.viewmodel.StudentViewModel

@Composable
fun MainScreen(
    viewModel: StudentViewModel
) {
    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var editingSiswa by remember { mutableStateOf<Siswa?>(null) }

    val siswaList by viewModel.siswaList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Registrasi Siswa",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Kelola data siswa",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        FormInput(
            nama = nama,
            email = email,
            isEditMode = editingSiswa != null,
            onNamaChange = { nama = it },
            onEmailChange = { email = it },
            onSubmitClick = {
                if (nama.isBlank()) return@FormInput
                if (email.isBlank()) return@FormInput
                if (!email.contains("@")) return@FormInput

                if (editingSiswa != null) {
                    viewModel.editSiswa(editingSiswa!!.copy(nama = nama, email = email))
                    editingSiswa = null
                } else {
                    viewModel.tambahSiswa(nama, email)
                }

                nama = ""
                email = ""
            },
            onCancelEdit = {
                editingSiswa = null
                nama = ""
                email = ""
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (siswaList.isEmpty()) {
            Text(text = "Belum ada data siswa")
        }

        LazyColumn {
            items(siswaList) { siswa ->
                StudentItem(
                    siswa = siswa,
                    onDelete = { viewModel.hapusSiswa(siswa) },
                    onEdit = {
                        editingSiswa = siswa
                        nama = siswa.nama
                        email = siswa.email
                    }
                )
            }
        }
    }
}
