package me.buddha.calendarapp.data.network.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeleteTaskResponse(
  val status: String,
)