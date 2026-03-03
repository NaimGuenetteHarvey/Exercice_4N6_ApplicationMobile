package com.example.retrofitcomplexe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.retrofitcomplexe.ui.theme.RetrofitComplexeTheme
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitComplexeTheme {
                Greeting()
            }
        }
    }
}

private sealed interface UiState {
    data object Loading : UiState
    data class Success(val data: web) : UiState
    data class Error(val message: String) : UiState
}

@Composable
fun Greeting() {
    var name by remember { mutableStateOf("Naim") }
    var uiState by remember { mutableStateOf<UiState>(UiState.Loading) }

    fun loadData() {
        uiState = UiState.Loading
        RetrofitInstance.api.listReposString(name).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (!response.isSuccessful || response.body().isNullOrBlank()) {
                    uiState = UiState.Error("Erreur HTTP ${response.code()}")
                    return
                }

                runCatching { parseWeb(response.body()!!) }
                    .onSuccess { parsed -> uiState = UiState.Success(parsed) }
                    .onFailure { uiState = UiState.Error("JSON invalide: ${it.message}") }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                uiState = UiState.Error(t.message ?: "Erreur réseau inconnue")
            }
        })
    }

    LaunchedEffect(Unit) { loadData() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Exercice Retrofit", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nom") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(onClick = { loadData() }) {
            Text("Charger")
        }

        when (val state = uiState) {
            UiState.Loading -> {
                Spacer(modifier = Modifier.height(8.dp))
                CircularProgressIndicator()
                Text("Chargement...")
            }

            is UiState.Error -> {
                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            is UiState.Success -> {
                Text("a: ${state.data.a}")
                Text("b: ${state.data.b}")
                Text("c: ${state.data.c.joinToString()}")
            }
        }
    }
}

private fun parseWeb(json: String): web {
    val root = JSONObject(json)
    val array = root.getJSONArray("c")
    val values = buildList {
        for (index in 0 until array.length()) {
            add(array.getInt(index))
        }
    }

    return web(
        a = root.getInt("a"),
        b = root.getString("b"),
        c = values
    )
}
