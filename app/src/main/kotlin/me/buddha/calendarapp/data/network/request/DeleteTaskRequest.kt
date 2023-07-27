package me.buddha.calendarapp.data.network.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeleteTaskRequest(
  @Json(name = "user_id")
  val user_id: Int,
  @Json(name = "task_id")
  val task_id: Int,
)
