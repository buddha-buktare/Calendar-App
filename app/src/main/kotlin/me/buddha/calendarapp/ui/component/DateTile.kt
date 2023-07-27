package me.buddha.calendarapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun DateTile(
  date: Int,
  isToday: Boolean = false,
  isSelected: Boolean = false,
  isInCurrentMonth: Boolean = false,
  onDateClick: () -> Unit,
) {
  Box(
    modifier = Modifier
      .height(40.dp)
      .clip(CircleShape)
      .clickable(
        onClick = onDateClick,
        indication = null,
        interactionSource = MutableInteractionSource(),
      )
      .background(if (isSelected) Color.LightGray else Color.Transparent),
    contentAlignment = Alignment.Center,
  ) {
    Text(
      text = date.toString(),
      textAlign = TextAlign.Center,
      modifier = Modifier
        .alpha(if (isInCurrentMonth) 1f else 0.3f),
      fontSize = 20.sp,
    )
  }
}