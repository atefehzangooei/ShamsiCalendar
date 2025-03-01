package com.appcoding.movie.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val text : String,
    val check : Boolean,
    val date : String
)

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title : String,
    val text :String,
    val date : String
)
