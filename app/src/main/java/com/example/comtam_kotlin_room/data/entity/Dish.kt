package com.example.comtam_kotlin_room.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Dish(
    var nameDish: String,
    var price: Double,

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
