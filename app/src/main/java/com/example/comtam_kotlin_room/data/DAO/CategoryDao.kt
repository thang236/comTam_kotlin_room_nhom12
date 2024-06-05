package com.example.comtam_kotlin_room.data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.comtam_kotlin_room.data.entity.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("Select * From category")
    fun getAll() : Flow<List<Category>>

    @Upsert
    suspend fun upsertCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)
}