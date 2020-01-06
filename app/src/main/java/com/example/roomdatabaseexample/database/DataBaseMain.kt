package com.example.roomdatabaseexample.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabaseexample.application.MyApplication

@Database(entities = [(DataBaseEntity::class)], version = 1)
abstract class DataBase  : RoomDatabase() {
    abstract fun dataBaseDaoInterface(): DataBaseDAOInterface

    companion object {
        val dataBase = Room.databaseBuilder(
            MyApplication.getApplicationContext(),
            DataBase::class.java,
            "MyTaskDB"
        ).build()
    }
}