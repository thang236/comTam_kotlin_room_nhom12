package com.example.comtam_kotlin_room.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.comtam_kotlin_room.R
import com.example.comtam_kotlin_room.utils.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var repasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(Color(0xFF252121))
            .fillMaxSize()
            .padding(top = 70.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .width(250.dp)
                .height(250.dp)
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
                    focusedBorderColor = Color.Transparent,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0XFFD9D9D9),
                        RoundedCornerShape(16.dp)
                    ),
            )
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
                onValueChange = { email = it },
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
                        RoundedCornerShape(16.dp)
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
                        Icon(imageVector = image, contentDescription = if (passwordVisible) "Hide password" else "Show password")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0XFFD9D9D9),
                        RoundedCornerShape(16.dp)
                    ),
            )
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
                onValueChange = { repassword = it },
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
                        Icon(imageVector = image, contentDescription = if (repasswordVisible) "Hide password" else "Show password")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0XFFD9D9D9),
                        RoundedCornerShape(16.dp)
                    ),
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                modifier = Modifier
                    .height(48.dp)
            ) {
                Text(
                    text = "Sign Up",
                    color = Color(0XFFFFFFFF),
                    fontSize = 20.sp,
                )
            }

            Row() {
                Text(text = "Already have account?",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color(0xff808080)
                )
                TextButton(onClick = {navController.navigate(Route.Home.screen) },
                ) {
                    Text(text = " SIGN IN", color = Color(0xff303030)
                        , fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUp() {
    SignUp(rememberNavController())
}

