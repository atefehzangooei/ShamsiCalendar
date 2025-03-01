package com.appcoding.movie.screens

import android.hardware.camera2.params.ColorSpaceTransform
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.appcoding.movie.Dimens
import com.appcoding.movie.Functions.RightToLeftLayout
import com.appcoding.movie.Functions.ScreenHeight
import com.appcoding.movie.Functions.ScreenWidth
import com.appcoding.movie.R
import com.appcoding.movie.room.Note
import com.appcoding.movie.room.RoomDao
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(on_dismiss : ()->Unit, roomDao: RoomDao, date : String){

    val alert_height = ScreenHeight() - 100.dp
    val alert_width = ScreenWidth() - 50.dp
    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    val note_scop = rememberCoroutineScope()

    RightToLeftLayout {

        BasicAlertDialog(
            onDismissRequest = {
                on_dismiss()
                note_scop.launch {
                    roomDao.InsertNote(Note(title = title,
                        text = text,
                        date = date))
                }

            },

            properties = DialogProperties(dismissOnClickOutside = false),
            modifier = Modifier
                .width(alert_width)
                .height(alert_height)
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = colorResource(R.color.home_note),
                        shape = RoundedCornerShape(Dimens.home_corner)
                    )
            ) {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    textStyle = MaterialTheme.typography.titleMedium,
                    placeholder = { Text("عنوان") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    )
                )

                HorizontalDivider(modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = colorResource(R.color.add_note_horizontal_line)
                )

                TextField(value = text,
                    onValueChange = { text = it },
                    textStyle = MaterialTheme.typography.bodyMedium,
                    placeholder = { Text("متن") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    )
                )

            }
        }
    }


}
