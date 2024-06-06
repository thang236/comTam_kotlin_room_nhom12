package com.example.comtam_kotlin_room.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.comtam_kotlin_room.data.DAO.CategoryDao
import com.example.comtam_kotlin_room.data.entity.Category

@Database(
    entities = [Category ::class],
    version = 1
)
abstract class Database: RoomDatabase(){
    abstract val dao: CategoryDao
}