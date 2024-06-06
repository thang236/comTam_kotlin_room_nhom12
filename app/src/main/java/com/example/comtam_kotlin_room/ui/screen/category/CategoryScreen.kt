package com.example.comtam_kotlin_room.ui.screen.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.comtam_kotlin_room.R
import com.example.comtam_kotlin_room.data.entity.Category


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    state: CategoryState,
    onEvent: (CategoryEvent) -> Unit,
    navController: NavController,
){
    var showDialogAdd by remember { mutableStateOf(false) }
    if (showDialogAdd) {
        DialogAdd(
            onConfirmation = { showDialogAdd = false },
            state = state,
            onEvent = onEvent
        )
    }

    Scaffold(
        topBar = {
            Column(Modifier.fillMaxWidth()) {
                TopAppBar(
                    title = {
                        Row (modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.ArrowBackIosNew, contentDescription ="" ,
                                Modifier.clickable { navController.popBackStack() })
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription ="",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.fillMaxWidth(0.12f)
                            )
                            Text(text = "Cum tứm đim")

                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xff252121),
                        titleContentColor = Color.White,
                    ),

                    )
                Divider(thickness = 2.dp, color = Color.Black)
            }

        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialogAdd = true }, contentColor = Color.White, containerColor = Color(0xFF2F2D2D)) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add new category")
            }
        }
    ) {
            paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .background(Color(0xFF252121))
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.categorys.size) { index ->
                CategoryItem(
                    state = state,
                    index = index,
                    onEvent = onEvent
                )
            }
        }
    }
}


@Composable
fun CategoryItem(
    state: CategoryState,
    index: Int,
    onEvent: (CategoryEvent) -> Unit
){
    var showDialogDelete by remember { mutableStateOf(false) }

    if (showDialogDelete) {
        DialogDelete(
            onConfirmation = {
                onEvent(CategoryEvent.DeleteCategory(state.categorys[index]))
                showDialogDelete = false
            },
            onDismiss = { showDialogDelete = false }
        )
    }

    var showDialogEdit by remember { mutableStateOf(false) }
    if (showDialogEdit){
        DialogEdit(
            state = state,
            category = state.categorys[index],
            onDismiss = { showDialogEdit = false },
            onEvent = onEvent)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color(0xFF2F2D2D))
            .padding(12.dp)
            .padding(horizontal = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "${index + 1}.", fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(end = 15.dp)
        )
        Text(
            modifier = Modifier.fillMaxWidth(0.75f),
            text = state.categorys[index].nameCategory,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White)
        IconButton(onClick = {showDialogEdit = true}) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "", tint = Color.White)
        }
        IconButton(onClick = { showDialogDelete = true }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "", tint = Color.White)
        }
    }
}



