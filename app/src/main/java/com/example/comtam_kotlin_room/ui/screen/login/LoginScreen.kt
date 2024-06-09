package com.example.comtam_kotlin_room.ui.screen.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.comtam_kotlin_room.DATABASE_INSTANCE
import com.example.comtam_kotlin_room.R
import com.example.comtam_kotlin_room.utils.Route
import kotlin.math.log

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {


    val loginViewModel = LoginViewModel(DATABASE_INSTANCE.userDao)
    loginViewModel.insertSampleAdminIfNeeded()

    val isAuthenticated by loginViewModel.isAuthenticated.observeAsState()
    val role by loginViewModel.isRole.observeAsState()



    val context = LocalContext.current

    LaunchedEffect(key1 = isAuthenticated) { when (isAuthenticated) {
        true -> {
            Log.d("zzzzzzz", "login: $role")
            if (role == 0){
                navController.navigate(Route.Home.screen) {
                    popUpTo(Route.LOGIN.screen) { inclusive = true } }
            }else{
                navController.navigate(Route.NavigationUser.screen) {
                    popUpTo(Route.LOGIN.screen) { inclusive = true } }
            }

        }
        false -> {
            Toast.makeText(context, "Invalid username or password.", Toast.LENGTH_SHORT).show()
            loginViewModel.resetAuthenticationState() }
        null -> {} }
    }

    val usernameState by loginViewModel.username.observeAsState("")
    val isShowPasswordState by loginViewModel.isShowPassword.observeAsState(false)

    var username by remember { mutableStateOf(usernameState) }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(isShowPasswordState) }

    Column(
        modifier = Modifier
            .background(Color(0xFF252121))
            .fillMaxSize()
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .width(230.dp)
                .height(230.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Welcome",
            fontFamily = FontFamily.Serif,
            color = Color(0XFFFFFFFF),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Username",
                fontFamily = FontFamily.Serif,
                color = Color(0XFFFFFFFF),
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 12.dp, start = 10.dp),
                fontWeight = FontWeight.Bold,
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0XFFD9D9D9),
                        RoundedCornerShape(6.dp)
                    ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Password",
                fontFamily = FontFamily.Serif,
                color = Color(0XFFFFFFFF),
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 12.dp, start = 10.dp),
                fontWeight = FontWeight.Bold,
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    }

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = if (passwordVisible) "Hide password" else "Show password")
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0XFFD9D9D9),
                        RoundedCornerShape(6.dp)
                    ),
            )

            Spacer(modifier = Modifier.height(26.dp))
            TextButton(
                onClick = {
                    loginViewModel.login(username, password)
                },
                modifier = Modifier.background(
                    color = Color(0XFFFE724C),
                    RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = "Log In",
                    color = Color(0XFFFFFFFF),
                    fontSize = 20.sp,
                )
            }

            Spacer(modifier = Modifier.height(56.dp))
            Row{
                Text(text = "Already have account?",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color(0xff808080)
                )
                TextButton(onClick = {navController.navigate(Route.Register.screen)},
                ) {
                    Text(text = "Register", color = Color(0xffFFFFFF)
                        , fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLogin() {
    LoginScreen(rememberNavController())
}
