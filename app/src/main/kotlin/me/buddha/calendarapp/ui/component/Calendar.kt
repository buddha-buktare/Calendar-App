package me.buddha.calendarapp.ui.component

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import me.buddha.calendarapp.domain.Day
import me.buddha.calendarapp.ui.CalendarViewModel

@Composable
internal fun Calendar(
  viewModel: CalendarViewModel
) {
  LazyVerticalGrid(
    columns = GridCells.Fixed(7),
  ) {

    items(Day.values()) {
      DayTile(it.abbrev)
    }
    items(viewModel.previousMonthDatesList) { date ->
      DateTile(
        date = date,
        onDateClick = {
          viewModel.decrementMonth()
          viewModel.selectedDate = date
          viewModel.selectedMonth = viewModel.visibleMonth
          viewModel.selectedYear = viewModel.visibleYear
        }
      )
    }
    items((1..viewModel.totalDaysInVisibleMonth).toList()) { date ->
      DateTile(
        date = date,
        isInCurrentMonth = true,
        onDateClick = {
          viewModel.selectedDate = date
          viewModel.selectedMonth = viewModel.visibleMonth
          viewModel.selectedYear = viewModel.visibleYear
        },
        isSelected = viewModel.isSelectedDateVisible && viewModel.selectedDate == date,
        isToday = viewModel.todayYear == viewModel.visibleYear && viewModel.todayMonth == viewModel.selectedMonth && viewModel.todayDate == date
      )
    }
    items(viewModel.nextMonthDatesList) { date ->
      DateTile(
        date = date,
        onDateClick = {
          viewModel.incrementMonth()
          viewModel.selectedDate = date
          viewModel.selectedMonth = viewModel.visibleMonth
          viewModel.selectedYear = viewModel.visibleYear
        }
      )
    }

  }
}