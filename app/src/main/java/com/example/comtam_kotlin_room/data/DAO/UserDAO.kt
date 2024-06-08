package com.example.comtam_kotlin_room.data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.comtam_kotlin_room.data.entity.Category
import com.example.comtam_kotlin_room.data.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {

    @Query("Select * From users")
    fun getAll() : Flow<List<User>>

    @Insert
    suspend fun insertUser(user: User)

    @Query("Select * From users Where userName = :userName and password = :pass")
    fun getUserByUsernamePass(userName: String, pass: String) : Flow<User>
    @Query("SELECT * FROM users WHERE userName = :userName LIMIT 1")
    fun getUserByUsername(userName: String): User?
    @Upsert
    suspend fun updateUpdate(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}