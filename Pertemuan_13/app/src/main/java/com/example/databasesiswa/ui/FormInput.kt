package com.example.databasesiswa.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FormInput(
    nama: String,
    email: String,
    isEditMode: Boolean,
    onNamaChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onSubmitClick: () -> Unit,
    onCancelEdit: () -> Unit
) {
    Column {
        TextField(
            value = nama,
            onValueChange = onNamaChange,
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSubmitClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isEditMode) "Update Siswa" else "Tambah Siswa")
        }

        if (isEditMode) {
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = onCancelEdit,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Batal")
            }
        }
    }
}
