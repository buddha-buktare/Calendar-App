package me.buddha.calendarapp.data.network.response

import com.squareup.moshi.JsonClass
import me.buddha.calendarapp.data.model.Task

@JsonClass(generateAdapter = true)
data class GetTaskResponse(
  val tasks: List<Task>,
)
