package me.buddha.calendarapp.domain.extention

fun <T> MutableList<T>.addData(list: List<T>) {
  this.addAll(list)
  val distinctList = this.distinctBy { it.hashCode() }
  this.clear()
  this.addAll(distinctList)
}