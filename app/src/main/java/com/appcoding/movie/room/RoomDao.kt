package com.appcoding.movie.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {

    //to do table
    @Insert
    suspend fun InsertTodo(todo : Todo)

    @Query("SELECT * FROM todo  where date = :date ORDER BY id DESC")
    fun GetAllTodo(date : String) : Flow<List<Todo>>

    @Query("delete from todo where id = :id and date = :date")
    suspend fun DeleteTodo(id: Int, date : String)


    //note table
    @Insert
    suspend fun InsertNote(note : Note)

    @Query("SELECT * FROM note where date = :date ORDER BY id DESC")
    fun GetAllNotes(date : String) : Flow<List<Note>>

    @Query("delete from note where id = :id and date = :date")
    suspend fun DeleteNote(id : Int, date :String)
}