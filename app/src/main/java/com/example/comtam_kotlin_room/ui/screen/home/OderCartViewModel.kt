package com.example.comtam_kotlin_room.ui.screen.home

import androidx.lifecycle.ViewModel
import com.example.comtam_kotlin_room.data.DAO.OderCartDao
import com.example.comtam_kotlin_room.data.entity.OderCart
import kotlinx.coroutines.flow.Flow

class OderCartViewModel(
    private val oderCartDao: OderCartDao
):ViewModel() {
    val allOders:Flow<List<OderCart>> = oderCartDao.getAllOderCart()
}