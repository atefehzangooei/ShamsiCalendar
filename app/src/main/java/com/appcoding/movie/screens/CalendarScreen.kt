package com.appcoding.movie.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.appcoding.movie.Dimens
import com.appcoding.movie.Functions.ConvertGeotgianToPersian
import com.appcoding.movie.Functions.RightToLeftLayout
import com.appcoding.movie.Functions.ScreenWidth
import com.appcoding.movie.R
import com.appcoding.movie.ui.theme.MovieTheme

@Composable
fun CalendarScreen(navController : NavHostController, date : String) {

    RightToLeftLayout {

        val month_days = ConvertGeotgianToPersian(date, "days")
        val month_name = ConvertGeotgianToPersian(date, "month")
        val day_card_size = ScreenWidth()/5

        Scaffold()
        { contentPadding ->

            //image background
            Box(
                modifier = Modifier
                    .fillMaxSize()
                //.background(colorResource(R.color.home_background))
            ) {

                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(R.drawable.wall_2),
                    contentDescription = "wallpaper",
                    contentScale = ContentScale.Crop,
                    alpha = 0.7f
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPadding)
                        .padding(Dimens.home_padding)
                ) {

                    Spacer(modifier = Modifier.size(Dimens.home_spacer))

                    //months
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(color = colorResource(R.color.calendar_month_background),
                            shape = RoundedCornerShape(Dimens.home_corner))
                        .padding(Dimens.home_padding),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically)
                    {
                        Icon(Icons.Filled.KeyboardArrowRight,
                            tint = colorResource(R.color.calendar_month_next_back),
                            contentDescription = "next"
                        )

                        Text(text = month_name,
                            style = MaterialTheme.typography.titleLarge,
                            color = colorResource(R.color.calendar_month_text)
                        )

                        Icon(Icons.Filled.KeyboardArrowLeft,
                            tint = colorResource(R.color.calendar_month_next_back),
                            contentDescription = "back"
                        )
                    }

                    Spacer(modifier = Modifier.size(Dimens.home_spacer))

                    //days
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight())
                    {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(5),
                            modifier = Modifier.wrapContentSize(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            items(month_days.toInt()){item ->
                                DayCard(item, day_card_size)
                            }
                        }
                    }

                }
            }
        }


    }
}

@Composable
fun DayCard(item : Int, size : Dp){

    Card(modifier = Modifier.size(size).padding(5.dp),
        colors =  CardDefaults.cardColors(
            containerColor = colorResource(R.color.calendar_days_background),
            contentColor = colorResource(R.color.calendar_days_text)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    )
    {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Text(
                text = (item + 1).toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalendar(){
    MovieTheme {
        val nav_controller = rememberNavController()
        CalendarScreen(nav_controller, "2025-02-16")
    }
}