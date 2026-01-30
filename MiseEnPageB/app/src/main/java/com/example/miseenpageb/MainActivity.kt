package com.example.miseenpageb

import android.graphics.Color.green
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.miseenpageb.ui.theme.MiseEnPageBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiseEnPageBTheme {

                    Greeting(

                    )

            }
        }
    }
}

@Composable
fun Greeting() {

   Row(modifier = Modifier
       .fillMaxSize())
   {
       Box(modifier = Modifier
           .fillMaxSize()
           .weight(1f)
           .background(color = Color.Green)) {}

       Box(modifier = Modifier
           .fillMaxSize()
           .weight(1f)
           .background(color = Color.White)){}

       Box(modifier = Modifier
           .fillMaxSize()
           .weight(1f)
           .background(color = Color.Red)){}
   }
}

