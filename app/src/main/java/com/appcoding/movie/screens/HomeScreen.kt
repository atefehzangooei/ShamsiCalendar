package com.appcoding.movie.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.appcoding.movie.Dimens
import com.appcoding.movie.Functions
import com.appcoding.movie.Functions.ConvertGeotgianToPersian
import com.appcoding.movie.Functions.RightToLeftLayout
import com.appcoding.movie.Functions.ScreenHeight
import com.appcoding.movie.R
import com.appcoding.movie.room.AppDatabase
import com.appcoding.movie.room.Note
import com.appcoding.movie.room.RoomDao
import com.appcoding.movie.room.Todo
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils


import com.appcoding.movie.ui.theme.MovieTheme
import kotlinx.coroutines.launch
import java.time.LocalDate
import androidx.compose.material3.ModalBottomSheet as ModalBottomSheet


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Composable
fun HomeScreen(navController: NavController, db : AppDatabase?){

    RightToLeftLayout{

        val insta_icons = listOf(
            Icons(R.drawable.post, "پست"),
            Icons(R.drawable.story, "استوری"),
            Icons(R.drawable.reels, "ریلز"),
            Icons(R.drawable.ads, "همکاری"),
        )

        var screen_height = ScreenHeight()
        var height_size by remember { mutableStateOf(0.dp) }
        val weight = 4.2f
        val sum_spacer = Dimens.home_spacer * 4 + Dimens.home_padding


        val today = remember { LocalDate.now().toString()}


        val shamsi_date = remember { ConvertGeotgianToPersian(today,"convert")}
        var day_name = remember { ConvertGeotgianToPersian(today,"day")}
        var month_name = remember { ConvertGeotgianToPersian(today,"month")}
        val shamsi_day = shamsi_date.split("/")[2]
        val shamsi_year = shamsi_date.split("/")[0]
        val days = remember { ConvertGeotgianToPersian(today,"days").toInt() }

        val sheetState = rememberModalBottomSheetState()
        val scope = rememberCoroutineScope()
        var todo_text by remember { mutableStateOf("") }

        val room_scop = rememberCoroutineScope()
        val room_dao = db!!.TodoDao()


        Scaffold()
        {contectPadding->

            //image background
            Box(modifier = Modifier
                .fillMaxSize()
                //.background(colorResource(R.color.home_background))
            ) {

               Image(modifier = Modifier.fillMaxSize(),
                    painter = painterResource(R.drawable.wall_2),
                    contentDescription = "wallpaper",
                    contentScale = ContentScale.Crop,
                    alpha = 0.7f)

                //content
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contectPadding)
                        // .background(color = colorResource(R.color.home_background))
                        .padding(Dimens.home_padding)
                )
                {
                   // top spacer
                    Spacer(modifier = Modifier.size(Dimens.home_spacer))

                    //calendar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.3f)
                            .background(
                                color = colorResource(R.color.home_calendar_background),
                                shape = RoundedCornerShape(Dimens.home_corner)
                            )
                            .padding(Dimens.home_calendar_padding)
                            .clickable {
                                navController.navigate(
                                    "calendar/{date}"
                                        .replace("{date}", today)
                                )
                            }

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
                                    NumberItem(index + 1, shamsi_day)

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
                                    text = day_name,
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
                                    text = "$shamsi_day $month_name $shamsi_year",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = colorResource(R.color.home_calendar_day),
                                    fontWeight = FontWeight.Bold
                                )
                            }


                        }
                    }




                    Spacer(modifier = Modifier.size(Dimens.home_spacer))

                    //todo and icons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f)


                    )
                    {

                        height_size = screen_height * (2f / weight)
                        height_size -= sum_spacer
                        height_size /= 2

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()

                        )
                        {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalArrangement = Arrangement.SpaceAround
                            ) {
                                items(insta_icons) { item ->
                                    IconCard(item, height_size, navController)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.size(Dimens.home_spacer_todo_icons))


                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .background(
                                    color = colorResource(R.color.home_todo_list),
                                    shape = RoundedCornerShape(Dimens.home_corner)
                                )
                                .padding(Dimens.home_todo_padding),
                                    contentAlignment = Alignment.BottomEnd
                        ) {

                            Column(
                                modifier = Modifier.fillMaxSize()

                            ) {
                                Text(
                                    text = "To Do",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )


                                // lazycolumn to display to do list
                                var todo_list by remember {
                                    mutableStateOf<List<Todo>>(emptyList())
                                }
                                LaunchedEffect(Unit) {
                                    room_dao.GetAllTodo(today).collect { todo_list = it }
                                }

                                LazyColumn(
                                    modifier = Modifier
                                        .wrapContentHeight()
                                        .fillMaxWidth()
                                ) {
                                    items(todo_list) { item ->
                                        TodoCard(item, room_dao)
                                    }

                                }

                            }

                            FloatingActionButton(
                                onClick = {
                                    scope.launch {
                                        sheetState.show()
                                    }
                                },
                                shape = CircleShape,
                                containerColor = colorResource(R.color.add_todo_background),
                                contentColor = colorResource(R.color.add_todo),
                                modifier = Modifier
                                    .size(Dimens.home_add_todo)

                            ) {
                                Icon(
                                    androidx.compose.material.icons.Icons.Filled.Add,
                                    contentDescription = "add todo"
                                )
                            }



                        }
                    }
                    


                    if(sheetState.isVisible) {
                        ModalBottomSheet(
                            onDismissRequest = {
                               scope.launch { sheetState.hide() }
                            },
                            sheetState = sheetState,
                            containerColor = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        )
                        {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                )
                            {
                              
                                TextField(value = todo_text,
                                        onValueChange = { todo_text = it },
                                        placeholder = { Text(text = "هر چی دوست داری بنویس") },
                                        singleLine = true,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(Dimens.home_padding),
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = Color.White,
                                        unfocusedContainerColor = Color.White,
                                        focusedIndicatorColor = Color.White,
                                        unfocusedIndicatorColor = Color.White
                                    ),
                                    trailingIcon = {
                                        Icon(androidx.compose.material.icons.Icons.Filled.Check,
                                            contentDescription = "add todo tik",
                                            tint = colorResource(R.color.add_todo_tik),
                                            modifier = Modifier
                                                .weight(1f)
                                                .wrapContentHeight()
                                                .clickable {
                                                    room_scop.launch {
                                                        room_dao.InsertTodo(
                                                            Todo(
                                                                text = todo_text,
                                                                check = false,
                                                                date = today
                                                            )
                                                        )
                                                        todo_text = ""
                                                        sheetState.hide()
                                                    }
                                                }
                                        )
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Done
                                    )
                                    )





                            }
                        }
                    }


                    Spacer(modifier = Modifier.size(Dimens.home_spacer))

                    //notes
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(
                                color = colorResource(R.color.home_note),
                                shape = RoundedCornerShape(Dimens.home_corner)
                            )
                    ) {
                        HomeScreen_Note(room_dao, today)


                    }

                    Spacer(modifier = Modifier.size(Dimens.home_spacer))


                }
            }
        }

    }
}

@Composable
fun HomeScreen_Note(room_dao: RoomDao, today : String) {

    var add_note_dialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize())
    {
        //note title - add note
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically)
        {
            Text(text = "یادداشیت ها",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )

            FloatingActionButton(onClick = {add_note_dialog = true},
                shape = CircleShape,
                containerColor = colorResource(R.color.add_todo_background),
                contentColor = colorResource(R.color.add_todo),
                modifier = Modifier.size(Dimens.home_add_todo)
                )
            {
                Icon(androidx.compose.material.icons.Icons.Filled.Add,
                    contentDescription = "add note",
                    tint = colorResource(R.color.add_todo)
                )

            }
        }

        //lazy row - show all notes
        var note_list by remember { mutableStateOf<List<Note>>(emptyList()) }
        LaunchedEffect(Unit) {
            room_dao.GetAllNotes(today).collect { note_list = it }
        }

        LazyRow(modifier = Modifier.fillMaxWidth())
        {
            items(note_list){item ->
                NoteCard(item)
            }
        }



        if(add_note_dialog){
            NoteScreen(on_dismiss = {
                    add_note_dialog = false },
                room_dao, today)
        }
    }
}

@Composable
fun NoteCard(item : Note){

        val corner = 10.dp
        val cut = 30.dp
        Box(modifier  = Modifier.padding(20.dp).size(300.dp))
        {
            Canvas(modifier = Modifier.size(300.dp)) {
                val clipPath = Path().apply {
                    lineTo(size.width - cut.toPx(),0f)
                    lineTo(size.width, cut.toPx())
                    lineTo(size.width , size.height)
                    lineTo(0f,size.height)
                    close()
                }

                clipPath(clipPath) {
                    drawRoundRect(
                        color = Color.Cyan,
                        size = size,
                        cornerRadius = CornerRadius(corner.toPx())
                    )


                    drawRoundRect(
                        color = Color(
                            ColorUtils.blendARGB(Color.Cyan.toArgb(),
                            0x000000, 0.2f)),
                        topLeft = Offset(size.width - cut.toPx(), -100f),
                        size = Size(cut.toPx() + 100f, cut.toPx() + 100f),
                        cornerRadius = CornerRadius(corner.toPx())
                    )
                }
            }
            Text(modifier = Modifier.fillMaxSize(),
                text = item.title)
        }

}

@Composable
fun TodoCard(item: Todo, room_dao : RoomDao) {
    val delete_scope = rememberCoroutineScope()

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
        )
    {
        Icon(
            painter = painterResource(R.drawable.bulet),
            contentDescription = "todo bullet",
            modifier = Modifier
                .size(Dimens.todo_bullet)
                .padding(5.dp)
                .clickable {
                    delete_scope.launch {
                        room_dao.DeleteTodo(item.id, item.date)
                    }
                }

            )

        Text(text = item.text,
            style = MaterialTheme.typography.bodySmall)

    }
}

@Composable
fun NumberItem(i: Int, shamsi_day : String) {

    var color = colorResource(R.color.home_calendar_background)
    var shape = RoundedCornerShape(0.dp)

    if(shamsi_day.toInt() == i){
        color = colorResource(R.color.home_calendar_selected_day)
        shape = CircleShape

    }

    Text(text = i.toString(),
        style = MaterialTheme.typography.bodySmall,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = color, shape = shape)
    )
}

@Composable
fun IconCard(item: Icons, height_size : Dp, nav_controller : NavController) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(height_size)
        .padding(5.dp)
        .clickable {
            nav_controller.navigate("instagram")
        },
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.home_icon_box),
            contentColor = colorResource(R.color.home_icon_tint)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painterResource(item.icon),
                contentDescription = "post"
            )

            Spacer(modifier = Modifier.size(20.dp))

            Text(text = item.title,
                color = colorResource(R.color.home_icon_title),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


data class Icons(
    val icon : Int,
    val title : String
)


@Preview(showBackground = true)
@Composable
fun Preview(){
    MovieTheme {
        val nav_controller = rememberNavController()
        val db : AppDatabase? = null
        HomeScreen(nav_controller, db)
    }
}