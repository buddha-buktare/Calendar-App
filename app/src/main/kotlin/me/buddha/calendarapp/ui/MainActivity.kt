package me.buddha.calendarapp.ui

import android.os.Build.VERSION_CODES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import me.buddha.calendarapp.ui.theme.CalendarAppTheme

@AndroidEntryPoint
@RequiresApi(VERSION_CODES.O)
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CalendarAppTheme {
        CalendarScreen(
          viewModel = hiltViewModel()
        )
      }
    }
  }
}