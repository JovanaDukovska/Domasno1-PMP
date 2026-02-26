package uklo.edu.mk.PMP.domasno1

import androidx.compose.foundation.background
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.ButtonDefaults
import uklo.edu.mk.PMP.domasno1.ui.theme.Domasno1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Domasno1Theme {
                Domasno1App()
            }
        }
    }
}

@Composable
fun Domasno1App() {

    var text by remember { mutableStateOf("") }
    var items by remember {
        mutableStateOf(
            listOf(
                "AndroidFP",
                "Deitel",
                "Google",
                "iPhoneFP",
                "JavaFP",
                "JavaHTP"
            )
        )
    }

//definiranje na celta na layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        //za prebaruvanje "search"
        OutlinedTextField(
            value = text,
            onValueChange = { newText -> text = newText },
            label = { Text("Enter Twitter search query here") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFEABD5C),
                unfocusedBorderColor = Color(0xFFEABD5C),
                focusedLabelColor = Color(0xFFEABD5C),
                cursorColor = Color(0xFFEABD5C)
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

//kopche za dodavawe text odnosno Add kopche
        Button(
            onClick = {
                if (text.isNotBlank()) {
                    items = items + text
                    text = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF5B727),
                contentColor = Color.White
            )
        ) {
            Text("Add")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEABD5C))
                .padding(8.dp)
        ) {
            //naslov na listata so elementi
            Text(
                text = "Tagged Searches",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))
//Listata na dodadeni elementi odnosno dodaden tekst zbor i slichno
            LazyColumn {
                items(items) { item ->

                    //za moderen Material 3 izgled
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = item,
                                color = Color(0xFF1C1C83)
                            )
                            //definirana redica za sekoj dodaden element vo listata
                            Row {
                                TextButton(onClick = {
                                    items = items.filter { it != item }
                                }) {
                                    Text("Delete")
                                }

                                TextButton(onClick = { }) {
                                    Text("Edit")
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            //kopche za brishennje na site elementi od listata
            Button(
                onClick = { items = emptyList() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF020202),
                    contentColor = Color(0xFFFDFDFD)
                )
            ) {
                Text("Clear All")
            }
        }
    }
}