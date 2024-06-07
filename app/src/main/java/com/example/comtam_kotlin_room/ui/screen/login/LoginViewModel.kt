package com.example.comtam_kotlin_room.ui.screen.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comtam_kotlin_room.data.DAO.UserDAO
import com.example.comtam_kotlin_room.data.entity.User
import kotlinx.coroutines.launch

class LoginViewModel(private val dao: UserDAO) : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _isAuthenticated = MutableLiveData<Boolean?>()
    val isAuthenticated: LiveData<Boolean?> = _isAuthenticated

    private val _isShowPassword = MutableLiveData<Boolean>()
    val isShowPassword: LiveData<Boolean> = _isShowPassword

    fun insertSampleAdminIfNeeded () {

        viewModelScope.launch {
            var users : List<User> = listOf()
            dao.getAll().collect{
                users = it

                if (users.isEmpty()) {
                    dao.insertUser(User(userName = "admin1", password = "123", "admin1@gmail.com"))
                    dao.insertUser(User(userName = "admin2", password = "456", "admin2@gmail.com"))
                }
            }
        }
    }



    fun login(username: String, password: String) {

        viewModelScope.launch {

            val user = dao.getUserByUsernamePass(username, password)
            user.collect{
                if (it != null) {
                    _isAuthenticated.value = true
                } else {
                    _isAuthenticated.value = false
                }
            }
        }
//        if (username.equals("admin") && password.equals("123")) {
//            _isAuthenticated.value = true
//        } else {
//            _isAuthenticated.value = false
//        }
    }

    fun resetAuthenticationState() {
        _isAuthenticated.value = null
    }

}