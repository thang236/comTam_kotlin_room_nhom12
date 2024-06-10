package com.example.comtam_kotlin_room.ui.screen_user.home_user

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comtam_kotlin_room.R

data class Foodd(val id: Int, val name: String, val price: String, val image: Int)

val itemm = listOf(
    Foodd(1, "Sườn bì", "28K", R.drawable.comtam),
    Foodd(2, "Bì chả", "25K", R.drawable.comtam),
    Foodd(3, "Trứng chả", "25K", R.drawable.comtam),
    Foodd(4, "Sườn chả", "28K", R.drawable.comtam),
    Foodd(5, "Sườn bì chả", "35K", R.drawable.comtam),
    Foodd(6, "Sườn cay", "35K", R.drawable.comtam),
    Foodd(7, "Sườn trứng", "30K", R.drawable.comtam),
    Foodd(8, "Sườn trứng", "30K", R.drawable.comtam),
    Foodd(9, "Sườn trứng", "30K", R.drawable.comtam),
    Foodd(10, "Sườn trứng", "30K", R.drawable.comtam),
)

@Preview(showBackground = true)
@Composable
fun HomeUserScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xff252121))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.pizza),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp)
                    .size(320.dp, 150.dp),
                contentScale = ContentScale.Crop
            )
        }
        Divider(thickness = 2.dp, color = Color.Black)
        HomeSection()
        Food(itemm)
    }
}


@Composable
fun Food(items: List<Foodd>){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF252121)),
        verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
        itemsIndexed(items){index, item ->
            FoodItem(index+1,item)
        }
    }
}


@Composable
fun FoodItem(order: Int, item: Foodd){
    var soLuong = remember { mutableIntStateOf(1) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .padding(5.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2F2D2D))
    ){
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text(
                text = "$order",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = item.image),
                contentDescription = "",
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.56f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = item.name, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(text = item.price, fontSize = 16.sp, color = Color(0xFFFE724C))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.fillMaxSize().align(Alignment.CenterVertically)) {
                TangGiamSoLuong(soLuong)
            }

        }
    }
}

@Composable
fun TangGiamSoLuong(soLuong: MutableIntState) {
    val contex = LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(
            onClick = {
                if (soLuong.value > 1) {
                    soLuong.value -= 1
                } else {
                    Toast.makeText(contex, "Số lượng không thể nhỏ hơn 1", Toast.LENGTH_SHORT)
                        .show()
                }
            },
            modifier = Modifier
                .background(Color(0xff252121), shape = RoundedCornerShape(20.dp))
                .border(BorderStroke(1.dp, Color(0xffFE724C)), CircleShape)
                .size(30.dp),

            ) {
            Icon(
                Icons.Default.Remove,
                contentDescription = "",
                Modifier.size(24.dp),
                tint = Color(0xffFE724C)
            )
        }
        Text(text = "" + soLuong.value, color = Color.White, fontSize = 20.sp)
        IconButton(
            onClick = { soLuong.value += 1 },
            modifier = Modifier
                .background(Color(0xffFE724C), shape = RoundedCornerShape(20.dp))
                .size(30.dp),

            ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "",
                Modifier.size(24.dp),
                tint = Color.White
            )
        }
    }
}