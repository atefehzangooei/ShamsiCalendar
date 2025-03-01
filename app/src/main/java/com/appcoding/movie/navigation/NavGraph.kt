package com.appcoding.movie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.appcoding.movie.models.months
import com.appcoding.movie.room.AppDatabase
import com.appcoding.movie.screens.CalendarScreen
import com.appcoding.movie.screens.HomeScreen
import com.appcoding.movie.screens.PostScreen


@Composable
fun MyNavGraph(navController: NavHostController, db : AppDatabase? = null){

  NavHost(navController = navController, startDestination = "home")
    {
        composable("home"){
            HomeScreen(navController, db)
        }

        composable("calendar/{date}")
        { navigateData ->
            val date = navigateData.arguments!!.getString("date")
            CalendarScreen(navController, date = date!!)
        }

        composable("instagram") { PostScreen(navController) }
    }
}