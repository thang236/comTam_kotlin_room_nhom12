package com.example.comtam_kotlin_room.ui.screen.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comtam_kotlin_room.data.DAO.UserDAO
import com.example.comtam_kotlin_room.data.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(private val dao: UserDAO) : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _isAuthenticated = MutableLiveData<Boolean?>()
    val isAuthenticated: LiveData<Boolean?> = _isAuthenticated

    private val _isShowPassword = MutableLiveData<Boolean>()
    val isShowPassword: LiveData<Boolean> = _isShowPassword

    private val _isShowRePassword = MutableLiveData<Boolean>()
    val isRepassVisible: LiveData<Boolean> = _isShowRePassword

    suspend fun checkUsernameExists(username: String): Boolean {
        return withContext(Dispatchers.IO) {
            dao.getUserByUsername(username) != null
        }
    }

    fun register(user: User) {
        viewModelScope.launch {
            if (checkUsernameExists(user.userName)) {
                _isAuthenticated.value = false
            } else {
                dao.insertUser(user)
                _isAuthenticated.value = true
            }
        }
    }

    fun resetAuthenticationState() {
        _isAuthenticated.value = null
    }
}
