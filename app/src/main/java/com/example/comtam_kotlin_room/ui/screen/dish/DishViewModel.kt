package com.example.comtam_kotlin_room.ui.screen.dish


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comtam_kotlin_room.data.DAO.DishDao
import com.example.comtam_kotlin_room.data.entity.Dish
import com.example.comtam_kotlin_room.data.entity.OderCart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DishViewModel(
    private  val dao: DishDao
): ViewModel() {


     lateinit var arrayByte: ByteArray

    val all: Flow<List<Dish>> = dao.getAll()
     var dish by mutableStateOf("")
     val dishs = dao.getAll().stateIn(viewModelScope, SharingStarted.WhileSubscribed(),
        emptyList()
    )
    val _state = MutableStateFlow(DishState())
    val state = combine(_state, dishs){state, dishs ->
        state.copy(
            dishs = dishs
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(10000), DishState())

    fun onEvent(event: DishEvent){
        when(event){
            is DishEvent.DeleteDish ->{
                viewModelScope.launch {
                    dao.deleteDish(event.dish)
                }
            }
            is DishEvent.SaveDish ->{
                val dish = Dish(
                    nameDish = event.nameDish,
                    price = event.price,
                    idCategory = event.idCategory,
                    image = event.image,
                    type = event.type
                )
                viewModelScope.launch {
                    dao.upsertDish(dish)
                }
                _state.update {
                    it.copy(
                        nameDish = mutableStateOf(""),
                        price = mutableStateOf(.0)
                    )
                }
            }
            is DishEvent.EditDish ->{

                viewModelScope.launch {
                    dao.upsertDish(event.dish)
                }
                _state.update {
                    it.copy(
                        nameDish = mutableStateOf(""),
                        price = mutableStateOf(.0)
                    )
                }
            }
        }
    }

}