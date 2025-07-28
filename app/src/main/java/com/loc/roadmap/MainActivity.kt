package com.loc.roadmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.loc.roadmap.data.HabitDatabase
import com.loc.roadmap.data.HabitRepository
import com.loc.roadmap.ui.HabitListScreen
import com.loc.roadmap.ui.theme.RoadmapTheme
import com.loc.roadmap.viewmodel.HabitViewModel
import com.loc.roadmap.viewmodel.HabitViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = HabitDatabase.getDatabase(applicationContext).habitDao()
        val repository = HabitRepository(dao)
        val factory = HabitViewModelFactory(repository)

        enableEdgeToEdge()
        setContent {
            RoadmapTheme {
                val viewModel: HabitViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = factory)

                HabitListScreen(viewModel = viewModel)
            }
        }
    }
}
