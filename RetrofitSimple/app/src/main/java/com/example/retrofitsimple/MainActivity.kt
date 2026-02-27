package com.example.retrofitsimple

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ca.cem.composeretrofitbase.api.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var inputNumber by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Double le nombre",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            OutlinedTextField(
                value = inputNumber,
                onValueChange = { inputNumber = it },
                label = { Text("Entrez un nombre") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            Button(
                onClick = {
                    val number = inputNumber.toIntOrNull()
                    if (number != null) {
                        isLoading = true
                        resultText = ""

                        val call = RetrofitInstance.api.listReposString(number)
                        call.enqueue(object : Callback<String> {
                            override fun onResponse(call: Call<String>, response: Response<String>) {
                                isLoading = false
                                if (response.isSuccessful) {
                                    val result = response.body() ?: "Aucun résultat"
                                    resultText = "Résultat: $result"

                                    // Afficher dans un SnackBar
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = "Le double de $number est $result",
                                            duration = SnackbarDuration.Short
                                        )
                                    }

                                    Log.d("MainActivity", "Succès: $result")
                                } else {
                                    resultText = "Erreur: ${response.code()}"
                                    Log.e("MainActivity", "Erreur: ${response.code()}")
                                }
                            }

                            override fun onFailure(call: Call<String>, t: Throwable) {
                                isLoading = false
                                resultText = "Erreur: ${t.message}"
                                Log.e("MainActivity", "Échec de l'appel", t)
                            }
                        })
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Veuillez entrer un nombre valide",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                },
                enabled = !isLoading,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text("Doubler le nombre")
                }
            }

            if (resultText.isNotEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text(
                        text = resultText,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}