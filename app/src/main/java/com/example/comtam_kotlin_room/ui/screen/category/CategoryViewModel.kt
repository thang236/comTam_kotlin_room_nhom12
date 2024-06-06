package com.example.comtam_kotlin_room.ui.screen.category

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comtam_kotlin_room.MainActivity
import com.example.comtam_kotlin_room.data.DAO.CategoryDao
import com.example.comtam_kotlin_room.data.entity.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModel (
    private  val dao: CategoryDao
) : ViewModel(){
    private val categorys = dao.getAll().stateIn(viewModelScope, SharingStarted.WhileSubscribed(),
        emptyList()
    )
    val _state = MutableStateFlow(CategoryState())
    val state = combine(_state, categorys){state, categorys ->
        state.copy(
            categorys = categorys
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(10000),CategoryState())

    fun onEvent(event: CategoryEvent){
        when(event){
            is CategoryEvent.DeleteCategory ->{
                viewModelScope.launch {
                    dao.deleteCategory(event.category)
                }
            }
            is CategoryEvent.SaveCategory ->{
                val category = Category(
                    nameCategory = state.value.nameCategory.value
                )
                viewModelScope.launch {
                    dao.upsertCategory(category)
                }
                _state.update {
                    it.copy(
                        nameCategory = mutableStateOf("")
                    )
                }
            }
            is CategoryEvent.EditCategory ->{

                viewModelScope.launch {
                    dao.upsertCategory(event.category)
                }
                _state.update {
                    it.copy(
                        nameCategory = mutableStateOf("")
                    )
                }
            }
        }
    }

}