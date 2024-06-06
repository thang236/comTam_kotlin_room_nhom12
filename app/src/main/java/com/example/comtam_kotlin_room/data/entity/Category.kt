package com.example.comtam_kotlin_room.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    var nameCategory: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)