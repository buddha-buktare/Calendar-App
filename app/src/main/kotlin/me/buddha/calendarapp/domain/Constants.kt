package me.buddha.calendarapp.domain

class Constants {
  companion object {
    const val BASE_URL = "http://dev.frndapp.in:8080/"
    const val USER_ID = 75
  }
}

enum class Month(val value: String) {
  JANUARY("January"),
  FEBRUARY("February"),
  MARCH("March"),
  APRIL("April"),
  MAY("May"),
  JUNE("June"),
  JULY("July"),
  AUGUST("August"),
  SEPTEMBER("September"),
  OCTOBER("October"),
  NOVEMBER("November"),
  DECEMBER("December"),
}

enum class Day(val abbrev: String) {
  SUNDAY("Su"),
  MONDAY("Mo"),
  TUESDAY("Tu"),
  WEDNESDAY("We"),
  THURSDAY("Th"),
  FRIDAY("Fr"),
  SATURDAY("Sa"),
}