package com.example.retrofitsimple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ca.cem.composeretrofitbase.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {

    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Nombre") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            val number = input.toIntOrNull()

            if (number != null) {

                RetrofitInstance.api
                    .listReposString(number)
                    .enqueue(object : Callback<String> {

                        override fun onResponse(
                            call: Call<String>,
                            response: Response<String>
                        ) {
                            result = response.body() ?: "Erreur"
                        }

                        override fun onFailure(call: Call<String>, t: Throwable) {
                            result = t.message ?: "Erreur"
                        }
                    })
            }

        }) {
            Text("Doubler")
        }

        Text(text = result)
    }
}