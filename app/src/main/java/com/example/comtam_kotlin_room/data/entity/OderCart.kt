package com.example.comtam_kotlin_room.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "OderCart")
 data class OderCart (
    @PrimaryKey(autoGenerate = true) var idCart:Int,
    var idUser:Int,
    var total:Float,
    var status:Int,
)