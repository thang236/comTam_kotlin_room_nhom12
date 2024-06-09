package com.example.comtam_kotlin_room.ui.screen.dish

import android.annotation.SuppressLint
import android.content.ContentResolver
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


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddDishScreen(
    state: DishState,
    navigationController: NavHostController,
    onEvent: (DishEvent) -> Unit,
    categoryViewModel: CategoryViewModel
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
                            Icon(Icons.Default.ArrowBackIosNew, contentDescription ="" ,
                                Modifier.clickable { navigationController.popBackStack() })
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription ="",
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
            categoryViewModel = categoryViewModel
        )
    }
}


@SuppressLint("StateFlowValueCalledInComposition", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDish(
    state: DishState,
    navigationController: NavHostController,
    onEvent: (DishEvent) -> Unit,
    categoryViewModel: CategoryViewModel

) {
    var selectedMainCourse by remember { mutableStateOf("Món chính") }
    var selectedDishType by remember { mutableStateOf("chọn loại món") }
    val scrollState = rememberScrollState()



    val mainCourseOptions = listOf("Món chính", "Món phụ", "Topping")



    val dishTypeOptions = mutableListOf<String>()
    for (i in 0 until  categoryViewModel.state.value.categorys.size){
        dishTypeOptions.add(categoryViewModel.state.value.categorys[i].nameCategory)

    }


    val context = LocalContext.current
    var imageByte by remember { mutableStateOf<ByteArray?>(null) }


    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            imageUri = it
            imageByte = uriToByteArray(context.contentResolver, it)
            Log.d("zzzzzzzz", "AddDish: $imageByte")

        }
    }







    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(0xFF242020))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = imageUri , // Use a default image resource
            contentDescription = "",
            modifier = Modifier
                .padding(4.dp)
                .size(150.dp),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color.Gray, shape = RoundedCornerShape(8.dp))
                .clickable { getContent.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Thêm hình ảnh", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        var idCategory by remember { mutableIntStateOf(-1) }

        DropdownMenuBox(selectedMainCourse, mainCourseOptions) { selectedMainCourse = it }
        DropdownMenuBox(selectedDishType, dishTypeOptions) {

            selectedDishType = it
            for (i in 0 until categoryViewModel.state.value.categorys.size){
                if (categoryViewModel.state.value.categorys[i].nameCategory == it){
                        idCategory = categoryViewModel.state.value.categorys[i].id
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        var priceText by remember { mutableStateOf(state.price.value.toString()) }

        TextField(
            value = priceText,
            onValueChange = { newValue ->
                priceText = newValue
                newValue.toDoubleOrNull()?.let {
                    state.price.value = it
                }
            },
            label = { Text("Giá") },
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = state.nameDish.value,
            onValueChange = { state.nameDish.value = it },
            label = { Text("Tên món ăn") },
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (imageByte !=null){
                    onEvent(DishEvent.SaveDish(
                        nameDish = state.nameDish.value,
                        price = state.price.value,
                        idCategory = idCategory,
                        image = imageByte!!
                    ))
                    navigationController.popBackStack()
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
        val inputStream = contentResolver.openInputStream(uri)
        val byteArray = inputStream?.readBytes()
        byteArray
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}
