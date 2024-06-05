package com.example.comtam_kotlin_room.utils

sealed class Route(val screen: String) {
    data object Home : Route("home")
    data object WELCOME : Route("Welcome")
    data object LOGIN : Route("Login")
    data object THONGKE : Route("THONGKE")
    data object MANAGER : Route("MANAGER")
    data object SUPPORT : Route("SUPPORT")
    data object  CategoryScreen : Route ("CategoryScreen")
    data object AddCategory : Route("AddCategory")






}