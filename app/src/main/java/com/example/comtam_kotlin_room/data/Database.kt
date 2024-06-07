package com.example.comtam_kotlin_room.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.comtam_kotlin_room.data.DAO.CategoryDao
import com.example.comtam_kotlin_room.data.DAO.UserDAO
import com.example.comtam_kotlin_room.data.entity.Category
import com.example.comtam_kotlin_room.data.entity.UserModel

@Database(
    entities = [Category::class, UserModel::class],
    version = 1
)
abstract class Database: RoomDatabase(){
    abstract val categoryDao: CategoryDao

    abstract val userDao: UserDAO
}