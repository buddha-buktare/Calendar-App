package me.buddha.calendarapp.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun DayTile(
  day: String,
) {
  Box(
    modifier = Modifier
      .height(40.dp),
    contentAlignment = Alignment.Center,
  ) {
    Text(
      text = day,
      fontWeight = FontWeight.Bold,
      textAlign = TextAlign.Center,
    )
  }
}