package me.buddha.calendarapp.data.network.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.buddha.calendarapp.data.model.TaskDetail

@JsonClass(generateAdapter = true)
data class StoreTaskRequest(
  @Json(name = "user_id")
  val user_id: Int,
  val task: TaskDetail,
)
