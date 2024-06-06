package com.example.comtam_kotlin_room.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
    data class User(
    var userName: String,
    var password: String,
    var email: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)