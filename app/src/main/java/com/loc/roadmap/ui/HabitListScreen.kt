package com.loc.roadmap.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.loc.roadmap.viewmodel.HabitViewModel


@Composable
fun HabitListScreen(viewModel: HabitViewModel) {
    val habits by viewModel.habits.collectAsState()

    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Yeni alışkanlık") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    if (text.isNotBlank()) {
                        viewModel.addHabit(text)
                        text = ""
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Ekle")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(habits) { habit ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Checkbox(
                        checked = habit.isCompleted,
                        onCheckedChange = { viewModel.toggleHabit(habit) }
                    )
                    Text(
                        text = habit.title,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = { viewModel.deleteHabit(habit) }) {
                        Icon(Icons.Default.Delete, contentDescription = null)
                    }
                }
            }
        }
    }
}
