package com.example.roomdatabaseexample.database

import androidx.room.*

@Dao
interface DataBaseDAOInterface {

    @Query("SELECT * FROM DataBaseEntity")
   fun getNotesDetails():List<DataBaseEntity>

    @Insert
    fun insert(dataBaseEntity: DataBaseEntity)

    @Delete
    fun delete( dataBaseEntity: DataBaseEntity)

    @Update
    fun update( dataBaseEntity: DataBaseEntity)

}