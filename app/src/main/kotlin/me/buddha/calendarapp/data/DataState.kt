package me.buddha.calendarapp.data

import me.buddha.calendarapp.data.model.Error

data class DataState<out T>(
  val data: T? = null,
  val error: Error? = null,
  val isLoading: Boolean = false,
) {

  companion object {

    fun <T> success(data: T): DataState<T> {
      return DataState(data = data)
    }

    fun <T> loading(): DataState<T> =
      DataState(isLoading = true)

    fun <T> error(throwable: Throwable): DataState<T> {
      return DataState(
        error = Error(
          message = "Something went wrong! \n Please try again."
        )

      )
    }
  }
}