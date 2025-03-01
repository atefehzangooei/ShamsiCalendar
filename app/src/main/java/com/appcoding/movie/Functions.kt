package com.appcoding.movie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat
import java.text.SimpleDateFormat
import java.util.Locale

object Functions {


    @Composable
    fun RightToLeftLayout(content: @Composable () -> Unit) {
        CompositionLocalProvider(
            LocalLayoutDirection
                    provides LayoutDirection.Rtl
        ) {
            content()

        }
    }


    @Composable
    fun ScreenWidth(): Dp {
        val configuration = LocalConfiguration.current
        val screen_width = configuration.screenWidthDp.dp
        return screen_width
    }

    @Composable
    fun ScreenHeight(): Dp {
        val configuration = LocalConfiguration.current
        val screen_height = configuration.screenHeightDp.dp
        return screen_height
    }


    fun ConvertGeotgianToPersian(gregorianDate: String, tag: String): String {

        var result = ""


        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(gregorianDate)
        val persianDate = PersianDate(sdf)
        val formattedDate = PersianDateFormat.format(persianDate, "Y/m/d")


        when (tag) {
            "convert" -> result = formattedDate
            "day" -> result = persianDate.dayName()
            "month" -> result = persianDate.monthName()
            "days" -> result = persianDate.monthLength.toString()
        }
        return result

    }


}


