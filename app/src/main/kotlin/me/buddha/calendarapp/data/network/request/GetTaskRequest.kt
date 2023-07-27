package me.buddha.calendarapp.data.network.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetTaskRequest(
  @Json(name = "user_id")
  val user_id: Int,
)
