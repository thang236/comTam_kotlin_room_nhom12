package com.example.comtam_kotlin_room.data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.comtam_kotlin_room.data.entity.Dish
import kotlinx.coroutines.flow.Flow
@Dao
interface DishDao {
    @Query("Select * From dish")
    fun getAll() : Flow<List<Dish>>

    @Upsert
    suspend fun upsertDish(dish: Dish)

    @Delete
    suspend fun deleteDish(dish: Dish)
    @Query("SELECT * FROM dish WHERE id = :id LIMIT 1")
    fun getidbyidDish(id: String): Dish?

}