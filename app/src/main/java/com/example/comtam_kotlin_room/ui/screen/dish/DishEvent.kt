package com.example.comtam_kotlin_room.ui.screen.dish

import com.example.comtam_kotlin_room.data.entity.Dish


sealed interface DishEvent {
    data class SaveDish(
        val nameDish: String,
        val price: Double
    ): DishEvent

    data class DeleteDish(
        val dish: Dish
    ): DishEvent

    data class EditDish(
        val dish: Dish
    ): DishEvent
}