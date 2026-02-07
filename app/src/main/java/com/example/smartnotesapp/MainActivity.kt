package com.example.smartnotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartnotesapp.ui.theme.SmartNotesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartNotesAppTheme {
                NotesScreen()
            }
        }
    }
}

@Composable
fun NotesScreen() {
    var noteText by remember { mutableStateOf("") }
    var notesList by remember { mutableStateOf(listOf<String>()) }

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
            Text("Save Note")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Saved Notes:", style = MaterialTheme.typography.titleMedium)

        LazyColumn {
            items(notesList) { note ->
                Text(note, modifier = Modifier.padding(4.dp))
            }
        }
    }
}