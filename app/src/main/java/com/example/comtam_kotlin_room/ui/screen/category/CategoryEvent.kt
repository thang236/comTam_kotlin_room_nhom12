package com.example.comtam_kotlin_room.ui.screen.category

import com.example.comtam_kotlin_room.data.entity.Category

sealed interface CategoryEvent {
    data class SaveCategory(
        val nameCategory: String
    ): CategoryEvent

    data class DeleteCategory(
        val category: Category
    ): CategoryEvent

    data class EditCategory(
        val category: Category
    ): CategoryEvent
}