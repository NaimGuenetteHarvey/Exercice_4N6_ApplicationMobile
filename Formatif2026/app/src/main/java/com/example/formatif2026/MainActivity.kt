package com.example.formatif2026

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.formatif2026.ui.theme.Formatif2026Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            MiseEnPageTheme {

                AppNavigation()

            }

        }

    }

}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Accueil") {

        composable("Accueil") { Accueil(navController) }

        composable("Quitter") { Quitter(navController) }

    }

}

@OptIn(ExperimentalMaterial3Api::class)

@Composable

fun Accueil(navController: NavHostController){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(

        drawerState = drawerState,

        drawerContent = {

            ModalDrawerSheet {

                Spacer(modifier = Modifier.height(16.dp))

                NavigationDrawerItem(

                    label = { Text("Philippe") },

                    selected = true,

                    onClick = {

                        scope.launch { drawerState.close()}

                    },

                    modifier = Modifier.padding(horizontal = 12.dp)

                )

                NavigationDrawerItem(

                    label = { Text("Quitter") },

                    selected = false,

                    onClick = {

                        scope.launch { drawerState.close() }

                        navController.navigate("Quitter")

                    },

                    modifier = Modifier.padding(horizontal = 12.dp)

                )

            }

        }

    ) {

        Scaffold(

            modifier = Modifier.fillMaxSize(),

            topBar = {

                TopAppBar(

                    title = { Text("Philippe Giguere") },

                    navigationIcon = {

                        IconButton(onClick = {

                            scope.launch {

                                if (drawerState.isClosed) {

                                    drawerState.open()

                                } else {

                                    drawerState.close()

                                }

                            }

                        }) {

                            Icon(Icons.Filled.Menu, contentDescription = "Menu")

                        }

                    }

                )

            }

        ) { innerPadding ->

            Column(

                modifier = Modifier

                    .fillMaxSize()

                    .padding(innerPadding)

                    .padding(16.dp)

            ) {

            }

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)

@Composable

fun Quitter(navController: NavHostController) {

    var motDePasse by rememberSaveable { mutableStateOf("") }

    var Courriel by rememberSaveable { mutableStateOf("") }

    Column(

        modifier = Modifier

            .fillMaxSize(),

        verticalArrangement = Arrangement.Top

    )

    {

        Box(

            modifier = Modifier

                .fillMaxWidth()

                .padding(20.dp).

                height(150.dp)

                .background(color =Color.Blue)

        ) {

            Text(

                text = "Logo",

                fontSize = 40.sp

            )



        }

        TextField(

            value = Courriel,

            onValueChange = { Courriel = it },

            label = { Text(stringResource(R.string.Courriel)) },

            modifier = Modifier.fillMaxWidth().padding(20.dp)

        )

        TextField(

            value = motDePasse,

            onValueChange = { motDePasse = it },

            label = {Text(  stringResource(R.string.Motdepasse))},

            modifier = Modifier.fillMaxWidth().padding(20.dp),

            )

        Spacer(modifier = Modifier.weight(1f))

        Button(

            onClick = {navController.navigate("Accueil")}, modifier = Modifier.

            align(Alignment.End)

                .padding(10.dp)

        )

        {

            Text(

                text = "Go"

            )

        }

    }

}
