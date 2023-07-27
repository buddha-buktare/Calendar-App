package me.buddha.calendarapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import me.buddha.calendarapp.data.model.Task

@Composable
internal fun TaskItem(
  onDeleteClick: (Int) -> Unit,
  task: Task,
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .background(Color.LightGray, RoundedCornerShape(8.dp))
      .padding(
        horizontal = 16.dp,
        vertical = 8.dp,
      ),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Column(
      modifier = Modifier.weight(1f)
    ) {
      Text(
        text = task.task_detail.title,
        fontWeight = FontWeight.Bold,
      )
      Text(
        text = task.task_detail.description,
        fontWeight = FontWeight.Normal,
      )
    }
    Spacer(modifier = Modifier.width(8.dp))
    Button(
      onClick = {
        onDeleteClick(task.task_id)
      },
    ) {
      Text(text = "Delete")
    }
  }
}