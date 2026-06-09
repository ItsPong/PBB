package com.example.pertemuan_11

data class Product(
    val id: Long = System.currentTimeMillis(), // Membuat ID unik otomatis pakai waktu milidetik
    val name: String,
    val price: String,
    val description: String
)