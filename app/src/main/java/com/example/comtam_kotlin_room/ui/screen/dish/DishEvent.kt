package com.example.comtam_kotlin_room.ui.screen.dish

import android.icu.text.CaseMap.Title
import com.example.comtam_kotlin_room.data.entity.Dish


sealed interface DishEvent {
    data class SaveDish(
        val nameDish: String,
        val price: Double,
        var idCategory: Int,
        var image: ByteArray,
    ): DishEvent

    data class DeleteDish(
        val dish: Dish
    ): DishEvent

    data class EditDish(
        val dish: Dish
    ): DishEvent
}