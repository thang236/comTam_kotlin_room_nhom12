package com.example.comtam_kotlin_room.utils

sealed class Route(val screen: String) {
    data object Home : Route("home")
    data object DetailCart : Route("detail")
    data object WELCOME : Route("Welcome")
    data object LOGIN : Route("Login")
    data object Register : Route("Register")
    data object THONGKE : Route("THONGKE")
    data object History : Route("history")
    data object BieuDo : Route("bieudo")
    data object MANAGER : Route("MANAGER")
    data object SUPPORT : Route("SUPPORT")
    data object ManegerDish : Route("ManegerDish")
    data object AddDish : Route("AddDish")
    data object  CategoryScreen : Route ("CategoryScreen")
    data object AddCategory : Route("AddCategory")


    //User
    data object NavigationUser : Route("NavigationUser")
    data object HomeUser : Route("HomeUser")
    data object HistoryUser: Route("HistoryUser")
    data object CartUser : Route("CartUser")
    data object PersonUser : Route("PersonUser")
    data object EditPersonUser : Route("EditPersonUser")
    data object ChangImageUser : Route("ChangImageUser")













}