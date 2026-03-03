package com.example.retrofitcomplexe

import android.os.Bundle
import android.telecom.Call
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.retrofitcomplexe.ui.theme.RetrofitComplexeTheme

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

@Composable
fun Greeting() {


}
