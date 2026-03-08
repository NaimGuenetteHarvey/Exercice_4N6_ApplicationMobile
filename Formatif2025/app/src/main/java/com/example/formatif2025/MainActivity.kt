package com.example.formatif2025

import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formatif2025.ui.theme.Formatif2025Theme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column {
                Formatif2025Theme {

                    EcranReposGitHub(

                    )

                }
            }
        }
    }
}

@Composable
fun EcranReposGitHub(modifier: Modifier = Modifier) {
    var nombre by remember { mutableStateOf("") }
    var representations by remember { mutableStateOf<List<Representation>>(emptyList()) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                Instance.api.list(nombre).enqueue(object : Callback<List<Representation>> {
                    override fun onResponse(call: Call<List<Representation>>, response: Response<List<Representation>>) {
                        if (response.isSuccessful) {
                            representations = response.body() ?: emptyList()
                        }
                    }

                    override fun onFailure(call: Call<List<Representation>>, t: Throwable) {
                        Log.e("EcranReposGitHub", "Erreur lors de la récupération des repos", t)
                    }
                })
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Envoyer")
        }
        Spacer(modifier = Modifier.height(24.dp))
        if (representations.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(representations) { rep ->

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = rep.nombre
                        )
                        Text(
                            text = rep.description
                        )
                        Text(
                            text = rep.representation
                        )
                    }
                }
            }
        }
    }
}