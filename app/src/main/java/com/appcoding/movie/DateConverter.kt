package com.appcoding.movie

class DateConverter {

    private val gregorianToJalaliOffset =
        intArrayOf(0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365)

    // تابع برای تبدیل تاریخ میلادی به شمسی
    fun convertGregorianToPersian(year: Int, month: Int, day: Int): String {
        // محاسبه تعداد روزها از شروع سال میلادی
        val dayOfYear = if (isLeapYear(year) && month > 2) {
            gregorianToJalaliOffset[month - 1] + day + 1
        } else {
            gregorianToJalaliOffset[month - 1] + day
        }

        // محاسبه سال شمسی
        val persianYear = if (dayOfYear < 80) {
            year - 621
        } else {
            year - 622
        }

        // محاسبه روز شمسی
        val persianYearStartDay = if (dayOfYear < 80) {
            dayOfYear + 286 // ابتدای سال شمسی
        } else {
            dayOfYear - 79
        }

        // محاسبه ماه و روز شمسی
        val monthOfPersianYear = when {
            persianYearStartDay <= 31 -> 1
            persianYearStartDay <= 62 -> 2
            persianYearStartDay <= 93 -> 3
            persianYearStartDay <= 124 -> 4
            persianYearStartDay <= 155 -> 5
            persianYearStartDay <= 186 -> 6
            persianYearStartDay <= 217 -> 7
            persianYearStartDay <= 248 -> 8
            persianYearStartDay <= 279 -> 9
            persianYearStartDay <= 310 -> 10
            persianYearStartDay <= 341 -> 11
            else -> 12
        }

        // محاسبه روز شمسی
        val dayOfPersianYear = persianYearStartDay - gregorianToJalaliOffset[monthOfPersianYear - 1]

        // بازگشت تاریخ شمسی به صورت رشته
        return "$persianYear/$monthOfPersianYear/$dayOfPersianYear"
    }

    // تابعی برای بررسی سال کبیسه
    private fun isLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }
}
