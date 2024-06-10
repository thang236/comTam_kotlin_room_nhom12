package com.example.comtam_kotlin_room.ui.screen.dish

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.icu.text.CaseMap.Title
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.comtam_kotlin_room.R
import com.example.comtam_kotlin_room.ui.screen.category.CategoryViewModel
import com.example.comtam_kotlin_room.ui.theme.ComTam_kotlin_roomTheme
import com.example.comtam_kotlin_room.utils.Route
import java.io.IOException
import android.widget.Toast
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.ui.text.input.ImeAction
import com.example.comtam_kotlin_room.data.entity.Dish
import com.example.comtam_kotlin_room.ui.screen_user.home_user.items
import okhttp3.internal.format

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddDishScreen(
    state: DishState,
    navigationController: NavHostController,
    onEvent: (DishEvent) -> Unit,
    categoryViewModel: CategoryViewModel,
    dishViewModel: DishViewModel
) {

    Scaffold(
        topBar = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF242020))
            ) {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF242020)),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.ArrowBackIosNew, contentDescription = "",
                                Modifier.clickable { navigationController.popBackStack() })
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.fillMaxWidth(0.12f)
                            )
                            Text(text = "Cum tứm đim", color = Color.White)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF252121),
                        titleContentColor = Color.White,
                    ),
                )
                Divider(thickness = 2.dp, color = Color.Black)
            }
        },
    ) {
        AddDish(
            state = state,
            onEvent = onEvent,
            navigationController = navigationController,
            categoryViewModel = categoryViewModel,
            dishViewModel = dishViewModel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition", "UnrememberedMutableState")
@Composable
fun AddDish(
    state: DishState,
    navigationController: NavHostController,
    onEvent: (DishEvent) -> Unit,
    categoryViewModel: CategoryViewModel,
    dishViewModel: DishViewModel,
) {
    var selectedMainCourse by remember { mutableStateOf("Chọn loại") }
    val listDish = mutableListOf("món chính", "món phụ ", "Topping", "Khác")

    var selectedDishType by remember { mutableStateOf("chọn loại món") }
    val scrollState = rememberScrollState()

    val mainCourseOptions = categoryViewModel.state.value.categorys.map { it.nameCategory }

    val context = LocalContext.current
    var imageByte by remember { mutableStateOf<ByteArray?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            imageUri = it
            imageByte = uriToByteArray(context.contentResolver, it)
            Log.d("AddDish", "Image byte array: $imageByte")
        }
    }

    var idCategory by remember { mutableIntStateOf(-1) }
    var type by remember { mutableStateOf("") }
    var priceText by remember { mutableStateOf(state.price.value.takeIf { it != 0.0 }?.toString() ?: "") }
    var nameDish by remember { mutableStateOf(state.nameDish.value) }
    var isPriceError by remember { mutableStateOf(false) }
    var isNameDishError by remember { mutableStateOf(false) }
    var isDishTypeError by remember { mutableStateOf(false) }
    var isMainCourseError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(0xFF242020))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color.Gray, shape = RoundedCornerShape(8.dp))
                .clickable { getContent.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Thêm hình ảnh", color = Color.White)
            AsyncImage(
                model = imageUri,
                contentDescription = "",
                modifier = Modifier
                    .padding(4.dp)
                    .size(150.dp),
                contentScale = ContentScale.Crop,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        DropdownMenuBox(selectedMainCourse, mainCourseOptions) {
            selectedMainCourse = it
            isMainCourseError = false
            categoryViewModel.state.value.categorys.find { category ->
                category.nameCategory == it
            }?.let { category ->
                idCategory = category.id
            }
        }

        if (isMainCourseError) {
            Text(text = "Phải chọn loại", color = MaterialTheme.colorScheme.error)
        }

        DropdownMenuBox(selectedDishType, listDish) {
            selectedDishType = it
            isDishTypeError = false
            listDish.indexOf(it).takeIf { index -> index >= 0 }?.let { index ->
                state.type.value = index
            }
        }

        if (isDishTypeError) {
            Text(text = "Phải chọn loại món", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = priceText,
            onValueChange = { newValue ->
                priceText = newValue
                val price = newValue.toDoubleOrNull()

                isPriceError = when {
                    newValue.isBlank() -> true
                    price == null -> true
                    price <= 0 -> true
                    else -> false
                }

                if (!isPriceError) {
                    price?.let {
                        state.price.value = it
                    }
                }
            },
            label = { Text("Giá") },
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
            isError = isPriceError,
            keyboardActions = KeyboardActions(onDone = {
                when {
                    priceText.isBlank() -> {
                        isPriceError = true
                    }
                    priceText.toDoubleOrNull() == null -> {
                        isPriceError = true
                    }
                    priceText.toDoubleOrNull()!! <= 0 -> {
                        isPriceError = true
                    }
                    else -> {
                        isPriceError = false
                        state.price.value = priceText.toDoubleOrNull()!!
                    }
                }
            })
        )


        if (isPriceError) {
            when {
                priceText.isBlank() -> {
                    Text(text = "Giá không được để trống", color = MaterialTheme.colorScheme.error)
                }
                priceText.toDoubleOrNull() == null -> {
                    Text(text = "Giá phải là số", color = MaterialTheme.colorScheme.error)
                }
                priceText.toDoubleOrNull()!! <= 0 -> {
                    Text(text = "Giá phải lớn hơn 0", color = MaterialTheme.colorScheme.error)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = nameDish,
            onValueChange = {
                nameDish = it
                state.nameDish.value = it
                isNameDishError = it.isBlank()
            },
            label = { Text("Tên món ăn") },
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            isError = isNameDishError,
        )


        if (isNameDishError) {
            Text(text = "Tên món ăn không được rỗng", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val isValid = imageByte != null && !isPriceError && nameDish.isNotBlank() && idCategory != -1 && state.type.value != -1

                if (isValid) {
                    onEvent(
                        DishEvent.SaveDish(
                            nameDish = state.nameDish.value,
                            price = state.price.value,
                            idCategory = idCategory,
                            image = imageByte!!,
                            type = state.type.value
                        )
                    )
                    navigationController.popBackStack()
                } else {
                    if (imageByte == null) {
                        Toast.makeText(context, "Chưa thêm hình ảnh", Toast.LENGTH_SHORT).show()
                    }
                    if (priceText.isBlank() || priceText.toDoubleOrNull() == null || priceText.toDoubleOrNull()!! <= 0) {
                        isPriceError = true
                    }
                    if (nameDish.isBlank()) {
                        isNameDishError = true
                    }
                    if (idCategory == -1) {
                        isMainCourseError = true
                    }
                    if (state.type.value == -1) {
                        isDishTypeError = true
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA000))
        ) {
            Text("Thêm", color = Color.White, fontSize = 18.sp)
        }
    }
}

@Composable
fun DropdownMenuBox(
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.Gray, shape = RoundedCornerShape(4.dp))
            .clickable { expanded = true }
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = selectedOption, color = Color.White)
            IconButton(
                onClick = { expanded = true },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    text = { Text(text = option) }
                )
            }
        }
    }
}

fun uriToByteArray(contentResolver: ContentResolver, uri: Uri): ByteArray? {
    return try {
        contentResolver.openInputStream(uri)?.use { it.readBytes() }
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

@Composable
fun titleToString(title: Int): String {
    return when (title) {
        0 -> "Món chính"
        1 -> "Món phụ"
        2 -> "Topping"
        3 -> "Khác"
        else -> "Unknown"
    }
}