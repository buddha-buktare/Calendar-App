package me.buddha.calendarapp.domain.repository

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import me.buddha.calendarapp.data.DataState
import me.buddha.calendarapp.data.model.TaskDetail
import me.buddha.calendarapp.data.network.request.DeleteTaskRequest
import me.buddha.calendarapp.data.network.request.GetTaskRequest
import me.buddha.calendarapp.data.network.request.StoreTaskRequest
import me.buddha.calendarapp.data.network.service.ApiService
import javax.inject.Inject

class CalendarRepository @Inject constructor(
  private val service: ApiService
) {

  suspend fun storeCalendarTask(
    userId: Int,
    task: TaskDetail,
  ) = flow {
    emit(DataState.success(service.storeCalendarTask(StoreTaskRequest(userId, task))))
  }.catch {
    emit(DataState.error(it))
  }

  suspend fun getCalendarTask(
    userId: Int,
  ) = flow {
    emit(DataState.success(service.getCalendarTaskList(GetTaskRequest(userId))))
  }.catch {
    emit(DataState.error(it))
  }

  suspend fun deleteCalendarTask(
    userId: Int,
    taskId: Int,
  ) = flow {
    emit(DataState.success(service.deleteCalendarTask(DeleteTaskRequest(userId, taskId))))
  }.catch {
    emit(DataState.error(it))
  }
}