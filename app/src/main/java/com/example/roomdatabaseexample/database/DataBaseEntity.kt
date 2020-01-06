package com.example.roomdatabaseexample.database


import androidx.room.Entity

import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
class DataBaseEntity(
    var task: String? = null,
    var desc: String? = null,
    var finishBy: String? = null,
    var isFinished:Boolean = false
) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id = 0




}