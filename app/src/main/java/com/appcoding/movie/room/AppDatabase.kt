package com.appcoding.movie.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo :: class, Note ::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase :RoomDatabase() {
    abstract fun TodoDao() : RoomDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun GetDatabase(context : Context) : AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}