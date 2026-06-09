package com.example.pertemuan_11

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf("home") }
    val productList = remember { mutableStateListOf<Product>() }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        productList.add(Product(name = "Buku Matematika SMA", price = "85000", description = "Buku pelajaran matematika kelas 12 kurikulum merdeka"))
        productList.add(Product(name = "Kalkulator Scientific", price = "150000", description = "Kalkulator untuk ujian nasional dan praktikum"))
        productList.add(Product(name = "Seragam Pramuka", price = "120000", description = "Seragam pramuka lengkap dengan atribut"))
        productList.add(Product(name = "Tas Ransel Sekolah", price = "200000", description = "Tas ransel anti air kapasitas 30L"))
        productList.add(Product(name = "Buku Tulis 58 Lembar", price = "8000", description = "Buku tulis bergaris isi 58 lembar"))
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(if (currentScreen == "add") "Tambah Produk" else "MarketSiswa")
                },
                navigationIcon = {
                    if (currentScreen == "add") {
                        IconButton(onClick = { currentScreen = "home" }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Kembali"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentScreen == "home",
                    onClick = { currentScreen = "home" },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = currentScreen == "profile",
                    onClick = { currentScreen = "profile" },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profil") },
                    label = { Text("Profil") }
                )
            }
        },
        floatingActionButton = {
            if (currentScreen == "home") {
                ExtendedFloatingActionButton(
                    onClick = { currentScreen = "add" },
                    icon = { Icon(Icons.Default.Add, contentDescription = "Jual") },
                    text = { Text("Jual") }
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        when (currentScreen) {
            "home" -> HomeScreen(
                products = productList,
                modifier = Modifier.padding(innerPadding)
            )
            "add" -> AddProductScreen(
                modifier = Modifier.padding(innerPadding),
                onProductAdded = { product ->
                    productList.add(product)
                    currentScreen = "home"
                    scope.launch {
                        snackbarHostState.showSnackbar("Produk berhasil ditambahkan!")
                    }
                }
            )
            "profile" -> ProfileScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
