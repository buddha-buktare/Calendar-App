package me.buddha.calendarapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Task(
  @Json(name = "task_id")
  val task_id: Int,
  @Json(name = "task_detail")
  val task_detail: TaskDetail,
)
