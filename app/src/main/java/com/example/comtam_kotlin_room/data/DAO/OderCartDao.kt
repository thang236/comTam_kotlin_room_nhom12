package com.example.comtam_kotlin_room.data.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.comtam_kotlin_room.data.entity.OderCart
import kotlinx.coroutines.flow.Flow

@Dao
interface OderCartDao {
    @Query("SELECT * FROM OderCart")
    fun getAllOderCart(): Flow<List<OderCart>>
}