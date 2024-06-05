package com.example.comtam_kotlin_room.ui.screen.category

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.comtam_kotlin_room.data.entity.Category

data class CategoryState (
    val categorys: List<Category> = emptyList(),
    val nameCategory: MutableState<String> = mutableStateOf("")
)
