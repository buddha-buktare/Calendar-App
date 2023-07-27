package me.buddha.calendarapp.ui

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.buddha.calendarapp.data.model.Task
import me.buddha.calendarapp.data.model.TaskDetail
import me.buddha.calendarapp.domain.Constants.Companion.USER_ID
import me.buddha.calendarapp.domain.extention.addData
import me.buddha.calendarapp.domain.repository.CalendarRepository
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
  private val repository: CalendarRepository
) : ViewModel() {

  var selectedDate by mutableStateOf(0)
  var selectedMonth by mutableStateOf(0)
  var selectedYear by mutableStateOf(0)
  var visibleMonth by mutableStateOf(0)
  var visibleYear by mutableStateOf(0)
  val selectedDateFormat by derivedStateOf {
    mutableStateOf("$selectedDate/$selectedMonth/$selectedYear")
  }
  val isSelectedDateVisible by derivedStateOf {
    selectedYear == visibleYear && selectedMonth == visibleMonth
  }

  var todayDate by mutableStateOf(0)
  var todayMonth by mutableStateOf(0)
  var todayYear by mutableStateOf(0)
  val isToday by derivedStateOf {
    todayDate == selectedDate && todayMonth == visibleMonth && todayYear == visibleYear
  }

  var firstDayOfVisibleMonth by mutableStateOf(0)
  var totalDaysInVisibleMonth by mutableStateOf(0)
  var totalDaysInPreviousMonth by mutableStateOf(0)

  var previousMonthDatesList = mutableStateListOf<Int>()
  var nextMonthDatesList = mutableStateListOf<Int>()

  var userTasks = mutableStateListOf<Task>()

  init {
    getCurrentMonth()
    getTaskList()
  }

  fun getCurrentMonth() {
    val calendar = Calendar.getInstance()

    todayDate = calendar.get(Calendar.DATE)
    todayMonth = calendar.get(Calendar.MONTH)
    todayYear = calendar.get(Calendar.YEAR)

    selectedDate = todayDate
    selectedMonth = todayMonth
    selectedYear = todayYear
    visibleMonth = todayMonth
    visibleYear = todayYear

    getSelectedMonthDetails()
  }

  fun incrementMonth() {
    visibleMonth = (visibleMonth + 1) % 12
    if (visibleMonth == 0) visibleYear++
    getSelectedMonthDetails()
  }

  fun decrementMonth() {
    visibleMonth = (visibleMonth - 1 + 12) % 12
    if (visibleMonth == 11) visibleYear--
    getSelectedMonthDetails()
  }

  fun getSelectedMonthDetails() {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, visibleYear)
    calendar.set(Calendar.MONTH, visibleMonth)
    calendar.set(Calendar.DAY_OF_MONTH, 1)

    firstDayOfVisibleMonth = calendar.get(Calendar.DAY_OF_WEEK)
    totalDaysInVisibleMonth = calendar.getActualMaximum(Calendar.DATE)

    getPreviousMonthDetails(calendar)
  }

  private fun getPreviousMonthDetails(calendar: Calendar) {
    if (visibleMonth == 0) {
      calendar.set(Calendar.YEAR, visibleYear - 1)
      calendar.set(Calendar.MONTH, 11)
    } else {
      calendar.set(Calendar.MONTH, visibleMonth - 1)
    }
    totalDaysInPreviousMonth = calendar.getActualMaximum(Calendar.DATE)

    previousMonthDatesList.clear()
    nextMonthDatesList.clear()

    // Mathematics for Adding the dates visible from the previous month
    (totalDaysInPreviousMonth - firstDayOfVisibleMonth + 2..totalDaysInPreviousMonth).forEach { date ->
      previousMonthDatesList.add(date)
    }

    // Mathematics for Adding the dates visible from the next month
    (1..(42 - (totalDaysInVisibleMonth + firstDayOfVisibleMonth - 1))).forEach { date ->
      nextMonthDatesList.add(date)
    }
    if (nextMonthDatesList.size >= 7) {
      nextMonthDatesList = nextMonthDatesList.dropLast(7).toMutableStateList()
    }
  }

  internal fun storeTask(
    title: String,
    description: String = "",
  ) {
    viewModelScope.launch {
      repository.storeCalendarTask(
        USER_ID,
        TaskDetail(selectedDateFormat.value, title, description)
      ).onEach { response ->
        response.data?.run {
          if (status == "Success") {
            getTaskList()
          }
        }
      }.launchIn(viewModelScope)
    }
  }

  private fun getTaskList() {
    viewModelScope.launch {
      repository.getCalendarTask(USER_ID).onEach { response ->
        response.data?.let {
          userTasks.addData(it.tasks.toMutableList())
        }
      }.launchIn(viewModelScope)
    }
  }

  internal fun deleteTask(
    taskId: Int,
  ) {
    viewModelScope.launch {
      repository.deleteCalendarTask(
        USER_ID,
        taskId
      ).onEach { response ->
        response.data?.run {
          if (status == "Success") {
            userTasks.remove(userTasks.first { it.task_id == taskId })
          }
        }
      }.launchIn(viewModelScope)
    }
  }
}