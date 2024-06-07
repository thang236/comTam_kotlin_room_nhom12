package com.example.comtam_kotlin_room.data.DAO

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.comtam_kotlin_room.data.entity.Category
import com.example.comtam_kotlin_room.data.entity.UserModel
import kotlinx.coroutines.flow.Flow

interface UserDAO {

    @Query("Select * From users")
    fun getAll() : Flow<List<UserModel>>

    @Insert
    suspend fun insertUser(user: UserModel)

    @Query("Select * From users Where userName = :userName and password = :pass")
    fun getUserByUsernamePass(userName: String, pass: String) : Flow<UserModel>

    @Upsert
    suspend fun updateUpdate(user: UserModel)

    @Delete
    suspend fun deleteUser(user: UserModel)
}