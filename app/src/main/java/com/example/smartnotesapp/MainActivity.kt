package com.example.smartnotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartNotesApp()
        }
    }
}

@Composable
fun SmartNotesApp() {
    var notesList by remember { mutableStateOf(listOf<String>()) }
    var noteText by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = noteText,
            onValueChange = { noteText = it },
            label = { Text("Enter note") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (noteText.isNotBlank()) {
                    notesList = notesList + noteText
                    noteText = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Note")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(notesList) { note ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            notesList = notesList - note
                        }
                ) {
                    Text(
                        text = note,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "🗑️",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}