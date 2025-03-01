package com.appcoding.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appcoding.movie.Functions.ConvertGeotgianToPersian
import com.appcoding.movie.Functions.RightToLeftLayout
import com.appcoding.movie.screens.NumberItem
import java.time.LocalDate

@Composable
fun Calendar(){

    RightToLeftLayout {

        val today = remember { LocalDate.now().toString() }


         val shamsi_date = remember { ConvertGeotgianToPersian(today, "convert") }
        val dayName = remember { ConvertGeotgianToPersian(today, "day") }
        val monthName = remember { ConvertGeotgianToPersian(today, "month") }
        val shamsiDay = shamsi_date.split("/")[2]
        val shamsiYear = shamsi_date.split("/")[0]
        val days = remember { ConvertGeotgianToPersian(today, "days").toInt() }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(
                    color = colorResource(R.color.home_calendar_background),
                    shape = RoundedCornerShape(Dimens.home_corner)
                )
                .padding(Dimens.home_calendar_padding)


        )
        {
            Column(
                modifier = Modifier
                    .weight(1.1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(5),
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    items(days) { index ->
                        NumberItem(index + 1, shamsiDay)

                    }
                }
            }

            Box(
                modifier = Modifier
                    .weight(0.1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .padding(0.dp, 10.dp, 0.dp, 10.dp)
                        .background(Color.White)

                )
            }
            Column(
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(
                        text = dayName,
                        style = MaterialTheme.typography.titleLarge,
                        color = colorResource(R.color.home_calendar_dayofweek),
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(
                        text = "$shamsiDay $monthName $shamsiYear",
                        style = MaterialTheme.typography.titleMedium,
                        color = colorResource(R.color.home_calendar_day),
                        fontWeight = FontWeight.Bold
                    )
                }


            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview(){
    Calendar()
}