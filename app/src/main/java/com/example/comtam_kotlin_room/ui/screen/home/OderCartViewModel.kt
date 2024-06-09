package com.example.comtam_kotlin_room.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.comtam_kotlin_room.data.DAO.OderCartDao
import com.example.comtam_kotlin_room.data.entity.OderCart
import com.example.comtam_kotlin_room.utils.Route
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch

class OderCartViewModel(
    private val oderCartDao: OderCartDao,


):ViewModel() {
    val allOders:Flow<List<OderCart>> = oderCartDao.getAllOderCart()
    private val _updateResult = mutableStateOf<Result<Unit>?>(null)
    val updateResult: State<Result<Unit>?> = _updateResult

    // Hàm cập nhật trạng thái của tất cả các đơn hàng
    fun updateAllOrdersStatus(orderId: Int, newStatus: Int) {
        viewModelScope.launch {
            try {
                // Gọi hàm cập nhật trạng thái từ DAO
                oderCartDao.updateOrderStatus(orderId, newStatus)
                _updateResult.value = Result.success(Unit)
            } catch (e: Exception) {
                _updateResult.value = Result.failure(e)
            }
        }
    }
    }
