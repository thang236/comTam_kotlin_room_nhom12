package com.example.comtam_kotlin_room.ui.screen.category

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.comtam_kotlin_room.data.entity.Category

@Composable
fun DialogEdit(
    state: CategoryState,
    category: Category,
    onDismiss: () -> Unit,
    onEvent: (CategoryEvent) -> Unit
) {
    state.nameCategory.value = category.nameCategory
    var context = LocalContext.current

    Dialog(onDismissRequest = onDismiss) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = "Sửa loại món ăn",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            text = {
                TextField(
                    value = state.nameCategory.value,
                    onValueChange = { state.nameCategory.value = it }
                )
            },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            if (state.nameCategory.value.length < 4) {
                                Toast.makeText(
                                    context,
                                    "Loại món ăn không được để trống và phải có trên 4 ký tự",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                category.nameCategory = state.nameCategory.value
                                onEvent(CategoryEvent.EditCategory(category))
                                onDismiss()
                            }

                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFB703),
                            contentColor = Color.White
                        ),
                    ) {
                        Text("Lưu")
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFB703),
                            contentColor = Color.White
                        ),
                        modifier = Modifier.weight(1f),

                        ) {
                        Text("Hủy")
                    }
                }

            }
        )
    }
}