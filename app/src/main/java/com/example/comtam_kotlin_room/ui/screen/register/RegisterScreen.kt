package com.example.comtam_kotlin_room.ui.screen.register

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.comtam_kotlin_room.DATABASE_INSTANCE
import com.example.comtam_kotlin_room.R
import com.example.comtam_kotlin_room.data.entity.User
import com.example.comtam_kotlin_room.utils.Route
import java.lang.Integer.parseInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(navController: NavHostController) {
    val registerViewModel = RegisterViewModel(DATABASE_INSTANCE.userDao)
    val isAuthenticated by registerViewModel.isAuthenticated.observeAsState()

    val context = LocalContext.current

    LaunchedEffect(key1 = isAuthenticated) {
        when (isAuthenticated) {
            true -> {
                navController.navigate(Route.LOGIN.screen) {
                    popUpTo(Route.Register.screen) { inclusive = true }
                }
            }
            false -> {
                Toast.makeText(context, "Invalid username or password.", Toast.LENGTH_SHORT).show()
                registerViewModel.resetAuthenticationState()
            }
            null -> { }
        }
    }

    val usernameState by registerViewModel.username.observeAsState("")
    val isShowPasswordState by registerViewModel.isShowPassword.observeAsState(false)
    val isRePasswordState by registerViewModel.isRepassVisible.observeAsState(false)

    var username by remember { mutableStateOf(usernameState) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repassword by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var phuong by remember { mutableStateOf("") }
    var duong by remember { mutableStateOf("") }
    var soNha by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(isShowPasswordState) }
    var repasswordVisible by remember { mutableStateOf(isRePasswordState) }

    var usernameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var repasswordError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    var phoneNumberError by remember { mutableStateOf(false) }
    var phuongError by remember { mutableStateOf(false) }
    var duongError by remember { mutableStateOf(false) }
    var soNhaError by remember { mutableStateOf(false) }

    fun validatePhoneNumber(phone: String): Boolean {
        if (phone.isEmpty()) {
            return false
        }
        val phonePattern = "^[+]?[0-9]{10,13}\$".toRegex()
        return phone.matches(phonePattern)
    }
    LazyColumn(
        modifier = Modifier
            .background(Color(0xFF252121))
            .fillMaxSize()
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .width(220.dp)
                    .height(220.dp)
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
                    onValueChange = {
                        username = it
                        usernameError = username.isBlank()
                    },
                    isError = usernameError,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color(0XFFD9D9D9),
                            RoundedCornerShape(6.dp)
                        ),
                )
                if (usernameError) {
                    Text(
                        text = "Username cannot be empty",
                        color = Color(0XFFff770b),
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Email",
                    fontFamily = FontFamily.Serif,
                    color = Color(0XFFFFFFFF),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 12.dp, start = 10.dp),
                    fontWeight = FontWeight.Bold,
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                    },
                    isError = emailError,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color(0XFFD9D9D9),
                            RoundedCornerShape(6.dp)
                        ),
                )
                if (emailError) {
                    Text(
                        text = "Invalid email address",
                        color = Color(0XFFff770b),
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
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
                    onValueChange = {
                        password = it
                        passwordError = password.isBlank()
                    },
                    isError = passwordError,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                    ),
                    trailingIcon = {
                        val image = if (passwordVisible) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        }

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = image,
                                contentDescription = if (passwordVisible) "Hide password" else "Show password"
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color(0XFFD9D9D9),
                            RoundedCornerShape(6.dp)
                        ),
                )
                if (passwordError) {
                    Text(
                        text = "Password cannot be empty",
                        color = Color(0XFFff770b),
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Re-enter Password",
                    fontFamily = FontFamily.Serif,
                    color = Color(0XFFFFFFFF),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 12.dp, start = 10.dp),
                    fontWeight = FontWeight.Bold,
                )
                OutlinedTextField(
                    value = repassword,
                    onValueChange = {
                        repassword = it
                        repasswordError = repassword != password
                    },
                    isError = repasswordError,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = if (repasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                    ),
                    trailingIcon = {
                        val image = if (repasswordVisible) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        }

                        IconButton(onClick = { repasswordVisible = !repasswordVisible }) {
                            Icon(
                                imageVector = image,
                                contentDescription = if (repasswordVisible) "Hide password" else "Show password"
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color(0XFFD9D9D9),
                            RoundedCornerShape(6.dp)
                        ),
                )
                if (repasswordError) {
                    Text(
                        text = "Passwords do not match",
                        color = Color(0XFFff770b),
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "PhoneNumber",
                    fontFamily = FontFamily.Serif,
                    color = Color(0XFFFFFFFF),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 12.dp, start = 10.dp),
                    fontWeight = FontWeight.Bold,
                )
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = {
                        phoneNumber = it
                        phoneNumberError = !validatePhoneNumber(phoneNumber)
                    },
                    isError = phoneNumberError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color(0XFFD9D9D9),
                            RoundedCornerShape(6.dp)
                        ),
                )
                if (phoneNumberError) {
                    Text(
                        text = "Phone Number cannot be empty",
                        color = Color(0XFFff770b),
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Phường",
                    fontFamily = FontFamily.Serif,
                    color = Color(0XFFFFFFFF),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 12.dp, start = 10.dp),
                    fontWeight = FontWeight.Bold,
                )
                OutlinedTextField(
                    value = phuong,
                    onValueChange = {
                        phuong = it
                        phuongError = phuong.isBlank()
                    },
                    isError = phuongError,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color(0XFFD9D9D9),
                            RoundedCornerShape(6.dp)
                        ),
                )
                if (phuongError) {
                    Text(
                        text = "Phường cannot be empty",
                        color = Color(0XFFff770b),
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Đường",
                    fontFamily = FontFamily.Serif,
                    color = Color(0XFFFFFFFF),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 12.dp, start = 10.dp),
                    fontWeight = FontWeight.Bold,
                )
                OutlinedTextField(
                    value = duong,
                    onValueChange = {
                        duong = it
                        duongError = duong.isBlank()
                    },
                    isError = duongError,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color(0XFFD9D9D9),
                            RoundedCornerShape(6.dp)
                        ),
                )
                if (duongError) {
                    Text(
                        text = "Đường cannot be empty",
                        color = Color(0XFFff770b),
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Số Nhà",
                    fontFamily = FontFamily.Serif,
                    color = Color(0XFFFFFFFF),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 12.dp, start = 10.dp),
                    fontWeight = FontWeight.Bold,
                )
                OutlinedTextField(
                    value = soNha,
                    onValueChange = {
                        soNha = it
                        soNhaError = soNha.isBlank()
                    },
                    isError = soNhaError,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color(0XFFD9D9D9),
                            RoundedCornerShape(6.dp)
                        ),
                )
                if (soNhaError) {
                    Text(
                        text = "Số nhà cannot be empty",
                        color = Color(0XFFff770b),
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                TextButton(
                    onClick = {
                        usernameError = username.isBlank()
                        emailError = !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        passwordError = password.isBlank()
                        repasswordError = repassword != password
                        phoneNumberError = !validatePhoneNumber(phoneNumber)
                        phuongError = phuong.isBlank()
                        duongError = duong.isBlank()
                        soNhaError = soNha.isBlank()
                        val user = User(
                            username,
                            password,
                            email,
                            1,
                            parseInt(phoneNumber),
                            phuong,
                            duong,
                            parseInt(soNha)
                        )
                        if (!usernameError && !emailError && !passwordError && !repasswordError && !phoneNumberError && !phuongError && !duongError && !soNhaError) {
                            registerViewModel.register(user = user)
                            Toast.makeText(context, "Sign Up Success", Toast.LENGTH_LONG).show()
                            navController.navigate(Route.LOGIN.screen)
                        } else {
                            errorMessage = "Please correct the errors above"
                        }
                    },
                    modifier = Modifier.background(
                        color = Color(0XFFFE724C),
                        RoundedCornerShape(16.dp)
                    )
                ) {
                    Text(
                        text = "Xác Nhận",
                        color = Color(0XFFFFFFFF),
                        fontSize = 20.sp,
                    )
                }
                if (errorMessage.isNotEmpty()) {
                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                }
                Spacer(modifier = Modifier.height(26.dp))
                Row {
                    Text(
                        text = "Already have account?",
                        modifier = Modifier.align(Alignment.CenterVertically),
                        color = Color(0xff808080)
                    )
                    TextButton(
                        onClick = { navController.navigate(Route.LOGIN.screen) },
                    ) {
                        Text(
                            text = "Log In",
                            color = Color(0xffFFFFFF),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(36.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUp() {
    Register(rememberNavController())
}
