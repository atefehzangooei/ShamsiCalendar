package com.appcoding.movie.models

data class Months(
    val name : String,
    val days : Int
)

val months : List<Months> = listOf(
    Months("فروردین", 31),
    Months("اردیبهشت", 31),
    Months("خرداد", 31),
    Months("فروردین", 31),
    Months("تیر", 31),
    Months("مرداد", 31),
    Months("شهریور", 31),
    Months("ههر", 30),
    Months("آبان", 30),
    Months("آذر", 30),
    Months("دی", 30),
    Months("بهمن", 30),
    Months("اسفند", 30),

    )

fun GetDaysOfMonths(month_name : String) : Int{
    return months.find { it.name == month_name }!!.days
}