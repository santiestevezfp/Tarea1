package com.empresa.miclima

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.empresa.miclima.ui.theme.MiClimaTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiClimaTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "Tú clima")
                                }
                            }
                        )
                    }

                ) { innerPadding ->
                    // Descripción debajo del TopAppBar
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(16.dp)) // Agregamos un pequeño espacio debajo del TopAppBar

                        // Descripción de la funcionalidad
                        Text(
                            text = "Esta aplicación de clima te muestra el tiempo que hace en 5 ciudades",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(horizontal = 24.dp),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Aquí se añade la imagen
                        Image(
                            painter = painterResource(id = R.drawable.imagen_clima),
                            contentDescription = "Weather Icon",
                            modifier = Modifier
                                .size(150.dp)
                                .padding(8.dp),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Lista de elementos
                        WeatherList()

                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: La aplicación está visible")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: La aplicación está en primer plano")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: La aplicación ha sido pausada")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: La aplicación ya no está visible")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: La aplicación ha sido destruida")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.d(TAG, "onLowMemory: El sistema tiene poca memoria")
    }

}

// Composable para mostrar la lista
@Composable
fun WeatherList() {
    val weatherData = listOf(
        "Vigo: Soleado, 25°C",
        "Pontevedra: Nublado, 20°C",
        "A Coruña: Lluvia, 18°C",
        "Lugo: Tormenta, 22°C",
        "Santiago: Nublado, 20°C"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(weatherData) { weatherInfo ->
            WeatherItem(weatherInfo)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

// Composable para un elemento individual de la lista
@Composable
fun WeatherItem(info: String) {
    Text(
        text = info,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}