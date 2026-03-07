package com.example.retrofitlistes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retrofitlistes.ui.theme.RetrofitListesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitListesTheme {

                    Greeting()

            }
        }
    }
}

@Composable
fun Greeting() {



            var listeLong by remember { mutableStateOf<List<Long>>(emptyList()) }
            var listeTruc by remember { mutableStateOf<List<Truc>>(emptyList()) }

            LaunchedEffect(Unit) {

                val responseLong = RetrofitInstance.api.getLong().execute()
                if (responseLong.isSuccessful) {
                    listeLong = responseLong.body() ?: emptyList()
                }

                val responseTruc = RetrofitInstance.api.getTrucs().execute()
                if (responseTruc.isSuccessful) {
                    listeTruc = responseTruc.body() ?: emptyList()
                }
            }

            Column(modifier = Modifier.fillMaxSize()) {

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(listeLong) { item ->
                        Text(
                            text = item.toString(),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(listeTruc) { truc ->
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {

                            Text(text = truc.a.toString())
                            Text(text = truc.b)
                            Text(text = "Taille liste: ${truc.c.size}")

                        }
                    }
                }

            }
        }





