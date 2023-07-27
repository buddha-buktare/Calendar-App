package me.buddha.calendarapp.ui

import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.buddha.calendarapp.ui.component.AddTask
import me.buddha.calendarapp.ui.component.Calendar
import me.buddha.calendarapp.ui.component.MonthTile
import me.buddha.calendarapp.ui.component.MonthYearDropdown
import me.buddha.calendarapp.ui.component.TaskItem

@RequiresApi(VERSION_CODES.O)
@Composable
fun CalendarScreen(
  viewModel: CalendarViewModel
) {

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(8.dp)
  ) {
    Text(
      text = "Calendar App",
      fontWeight = FontWeight.Bold,
      fontSize = 25.sp,
      textAlign = TextAlign.Center,
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)
    )
    MonthYearDropdown(
      visibleMonth = viewModel.visibleMonth,
      visibleYear = viewModel.visibleYear,
      setVisibleMonth = { month ->
        viewModel.visibleMonth = month
      },
      setVisibleYear = { year ->
        viewModel.visibleYear = year
      },
      getSelectedMonth = viewModel::getSelectedMonthDetails
    )
    MonthTile(
      visibleMonth = viewModel.visibleMonth,
      visibleYear = viewModel.visibleYear,
      incrementMonth = viewModel::incrementMonth,
      decrementMonth = viewModel::decrementMonth,
    )
    Calendar(
      viewModel = viewModel,
    )
    Spacer(modifier = Modifier.height(8.dp))
    LazyColumn(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)
    ) {
      item {
        AddTask(
          selectedDate = viewModel.selectedDate,
          selectedMonth = viewModel.selectedMonth,
          selectedYear = viewModel.selectedYear,
          storeTask = { title, description ->
            viewModel.storeTask(title, description)
          }
        )
      }
      item {
        Text(
          text = "Tasks: ",
          fontWeight = FontWeight.ExtraBold,
          fontSize = 20.sp,
          modifier = Modifier.padding(8.dp)
        )
      }
      items(
        viewModel.userTasks.filter { it.task_detail.created == viewModel.selectedDateFormat.value }
      ) { task ->
        TaskItem(
          onDeleteClick = viewModel::deleteTask,
          task = task,
        )
        Spacer(modifier = Modifier.height(4.dp))
      }
      if (!viewModel.isToday) {
        item {
          Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
          ) {
            Button(
              onClick = viewModel::getCurrentMonth,
            ) {
              Text(
                text = "Go To Today",
              )
            }
          }
        }
      }
    }
  }
}