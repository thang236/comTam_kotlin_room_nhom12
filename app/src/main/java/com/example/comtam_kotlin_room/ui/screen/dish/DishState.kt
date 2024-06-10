package com.example.comtam_kotlin_room.ui.screen.dish

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

import com.example.comtam_kotlin_room.data.entity.Dish

data class DishState(
    val dishs: List<Dish> = emptyList(),
    val nameDish: MutableState<String> = mutableStateOf(""),
    val idCategory: MutableState<Int> = mutableStateOf(-1),
    val price: MutableState<Double> = mutableStateOf(0.0),
    val id: MutableState<Int> = mutableStateOf(0),

    var image :  MutableState<ByteArray?> = mutableStateOf(null),
    var type: MutableState<Int> = mutableIntStateOf(-1)
)
