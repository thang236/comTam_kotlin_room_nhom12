package com.example.comtam_kotlin_room.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    var userName: String,
    var password: String,
    var email: String,
    var role: Int,
    var phoneNumber: Int? = null,
    var phuong: String? = null,
    var duong: String? = null,
    var soNha: Int? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    constructor(userName: String, password: String, email: String, role: Int) : this(
        userName,
        password,
        email,
        role,
        null,
        null,
        null,
        null
    )
}
