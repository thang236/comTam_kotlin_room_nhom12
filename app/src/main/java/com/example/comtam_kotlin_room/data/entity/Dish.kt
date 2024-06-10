package com.example.comtam_kotlin_room.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Dish(
    var nameDish: String,
    var price: Double,
    var idCategory: Int,
    var image: ByteArray,
    var type: Int,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
