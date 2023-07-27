package me.buddha.calendarapp.data.network.service

import me.buddha.calendarapp.data.network.request.DeleteTaskRequest
import me.buddha.calendarapp.data.network.request.GetTaskRequest
import me.buddha.calendarapp.data.network.request.StoreTaskRequest
import me.buddha.calendarapp.data.network.response.DeleteTaskResponse
import me.buddha.calendarapp.data.network.response.GetTaskResponse
import me.buddha.calendarapp.data.network.response.StoreTaskResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

  @POST("/api/storeCalendarTask")
  suspend fun storeCalendarTask(
    @Body request: StoreTaskRequest
  ): StoreTaskResponse

  @POST("/api/getCalendarTaskList")
  suspend fun getCalendarTaskList(
    @Body request: GetTaskRequest
  ): GetTaskResponse

  @POST("/api/deleteCalendarTask")
  suspend fun deleteCalendarTask(
    @Body request: DeleteTaskRequest
  ): DeleteTaskResponse
}