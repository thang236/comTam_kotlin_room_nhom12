package com.example.comtam_kotlin_room.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.comtam_kotlin_room.data.DAO.CategoryDao
import com.example.comtam_kotlin_room.data.DAO.OderCartDao
import com.example.comtam_kotlin_room.data.DAO.UserDAO
import com.example.comtam_kotlin_room.data.entity.Category
import com.example.comtam_kotlin_room.data.entity.OderCart
import com.example.comtam_kotlin_room.data.entity.User
  import com.example.comtam_kotlin_room.data.DAO.DishDao
import com.example.comtam_kotlin_room.data.entity.Dish

@Database(
    entities = [Category::class, User::class,OderCart::class ,Dish::class],
    version = 2
)
abstract class Database: RoomDatabase(){
    abstract val categoryDao: CategoryDao
    abstract val userDao: UserDAO
    abstract val oderCartDao:OderCartDao
    abstract val DishDao: DishDao


}