package com.example.miseenpaged

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miseenpaged.ui.theme.MiseEnPageDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiseEnPageDTheme {

                    Greeting()


            }
        }
    }
}

@Composable
fun Greeting() {
    Row(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(color = Color.Green)
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
                Box(modifier = Modifier.fillMaxSize().background(color = Color.Red).weight(1f)) {
                    Column(modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {

                        Button(onClick = {}) {Text(text = "Bouton") }
                        Button(onClick = {}) {Text(text = "Bouton") }
                    }

                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.fillMaxSize().background(color = Color.Red).weight(1f)) {
                    Row(modifier = Modifier.fillMaxSize(),

                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically

                        ) {

                        Button(onClick = {}) {Text(text = "Bouton") }
                        Button(onClick = {}) {Text(text = "Bouton") }
                    }


                }
            }
        }


        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(color = Color.White)
                .padding(10.dp)
        ) {

            Row(modifier = Modifier.fillMaxSize().padding(5.dp)) {
                Box(modifier = Modifier.fillMaxSize().background(color = Color.Black).weight(1f)) {}
                Spacer(modifier = Modifier.padding(10.dp))

                Box(modifier = Modifier.fillMaxSize().background(color = Color.Black).weight(1f)) {

                }
                Spacer(modifier = Modifier.padding(10.dp))
                Box(modifier = Modifier.fillMaxSize().background(color = Color.Black).weight(1f)) {}
            }
        }





        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(color = Color.Red)

        ) {

            Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {

                Row(modifier = Modifier.fillMaxWidth().padding(10.dp).weight(1f)) {


                    Box(modifier = Modifier.fillMaxSize().background(color = Color.Green).weight(1f)
                        .padding(5.dp)
                    ) {}
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier.fillMaxSize().background(color = Color.Green).weight(1f)
                            .padding(5.dp)
                    ) {}


                }

                Row(modifier = Modifier.fillMaxWidth().padding(10.dp).weight(1f)) {


                    Box(modifier = Modifier.fillMaxSize().background(color = Color.Green).weight(1f)
                        .padding(5.dp)
                    ) {}
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier.fillMaxSize().background(color = Color.Green).weight(1f)
                            .padding(5.dp)
                    ) {}


                }






            }
        }
    }
}

