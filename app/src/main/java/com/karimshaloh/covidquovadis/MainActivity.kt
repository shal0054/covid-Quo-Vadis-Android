package com.karimshaloh.covidquovadis

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karimshaloh.covidquovadis.ui.theme.CovidQuoVadisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CovidQuoVadisTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Display(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

val maxCapacity = 5

@Composable
fun Display(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Title()
        CountingWidget()
        RoomCapacity()
    }
}

@Composable
fun Title() {
    Text(
        text = stringResource(R.string.app_name),
        modifier = Modifier.padding(top = 24.dp),
        fontSize = 46.sp,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun CountingWidget() {
    var count by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val zeroToast =
        Toast.makeText(context, "There's no one left in the room!", Toast.LENGTH_LONG)
    val maxToast =
        Toast.makeText(context, "Room is at capacity!", Toast.LENGTH_LONG)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(onClick = {
            if (count == 0) {
                zeroToast.show()
            } else {
                count--
            }
        }) {
            Text("- 1", fontSize = 36.sp)
        }
        Text("$count", fontSize = 48.sp)
        Button(onClick = {
            if (count == maxCapacity) {
                maxToast.show()
            } else {
                count++
            }
        }) {
            Text("+ 1", fontSize = 36.sp)
        }
    }
}

@Composable
fun RoomCapacity() {
    Text(
        text = "The maximum room capacity is $maxCapacity",
        modifier = Modifier.padding(bottom = 36.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CovidQuoVadisTheme {
        Display()
    }
}
