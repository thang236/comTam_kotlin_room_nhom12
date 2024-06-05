package com.example.comtam_kotlin_room.ui.screen.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun DialogAdd(
    onConfirmation: () -> Unit,
    state: CategoryState,
    onEvent: (CategoryEvent) -> Unit
) {
    Dialog(onDismissRequest = { onConfirmation() }) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp,
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(text = "Thêm loại món ăn", style = MaterialTheme.typography.titleLarge, modifier = Modifier.align(Alignment.CenterHorizontally), fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.nameCategory.value,
                    onValueChange = { state.nameCategory.value = it },
                    textStyle = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                    ),
                    placeholder = {
                        Text(text = "Nhập loại món ăn")
                    })
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        onEvent(CategoryEvent.SaveCategory(state.nameCategory.value))
                        onConfirmation()
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFB703),
                        contentColor = Color.White
                    )
                ) {
                    Text("THÊM")
                }
            }
        }
    }
}