package com.example.exercicedessin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exercicedessin.ui.theme.ExerciceDessinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExerciceDessinTheme {

                    Greeting(

                    )

            }
        }
    }
}

@Composable
fun Greeting() {
    var courriel by rememberSaveable { mutableStateOf("") }
    var Password by rememberSaveable { mutableStateOf("") }
    Column() {
        OutlinedTextField(
            value = courriel,
            onValueChange = { courriel = it },
            label = { Text("Courriel") },
            modifier = Modifier.padding(16.dp)
        )
        OutlinedTextField(
            value = Password,
            onValueChange = { Password = it },
            label = { Text("Password") },
            modifier = Modifier.padding(16.dp),
            visualTransformation = PasswordVisualTransformation()

        )
        Button(onClick = {}) {
            Text(text = "Incription")
        }
        Button(onClick = {}) {
            Text(text = "Connexion")
        }









    }
}
