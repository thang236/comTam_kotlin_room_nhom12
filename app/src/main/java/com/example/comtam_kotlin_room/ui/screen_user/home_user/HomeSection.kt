package com.example.comtam_kotlin_room.ui.screen_user.home_user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comtam_kotlin_room.R

data class HomeSection(val id: Int, val name: String, val image: Int, var isSelected: Boolean = false)

val items = listOf(
    HomeSection(1, "Món ăn", R.drawable.comtam, false),
    HomeSection(2, "Đồ ăn thêm", R.drawable.comtam, false),
    HomeSection(1, "Topping", R.drawable.comtam, false),
    HomeSection(1, "Khác", R.drawable.comtam, false),
)

@Preview(showBackground = true)
@Composable
fun HomeSection() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xff252121)),
        horizontalArrangement = Arrangement.Center
    ) {
        items(items.size) { index ->
            HomeItem(index)
        }
    }
}

@Composable
fun HomeItem(index: Int) {
    val home = items[index]
    Box(
        modifier = Modifier
            .width(90.dp)
            .height(115.dp)
            .clickable { }
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = home.name, fontSize = 12.sp, color = Color.White, style = MaterialTheme.typography.labelLarge)
            Spacer(Modifier.height(10.dp))
            Image(
                painter = painterResource(id = home.image),
                contentDescription = "",
                modifier = Modifier
                    .size(70.dp)
                    .clip(shape = RoundedCornerShape(20.dp)),
                contentScale = ContentScale.FillHeight
            )
        }
    }
}