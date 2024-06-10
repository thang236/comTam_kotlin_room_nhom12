package com.example.comtam_kotlin_room.ui.screen_user.cart_user

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.comtam_kotlin_room.R

data class Foodd(val id: Int, val name: String, val price: String, val image: Int)
@Composable
fun CartUserScreen() {
    val mainItems = listOf(
        Foodd(1, "Sườn Bì", "56k", R.drawable.imgaesuonbi),
        Foodd(2, "Bì chả", "25k", R.drawable.imgaesuonbi),
        Foodd(3, "Bì trứng", "25k", R.drawable.imgaesuonbi)
    )

    val sideItems = listOf(
        Foodd(1, "Chả Giò", "15k", R.drawable.imgaesuonbi),
        Foodd(2, "Gỏi Cuốn", "20k", R.drawable.imgaesuonbi),
        Foodd(3, "Gỏi Cuốn", "20k", R.drawable.imgaesuonbi)
    )

    val toppingItems = listOf(
        Foodd(1, "Mỡ hành", "15k", R.drawable.imgaesuonbi),
        Foodd(2, "Tóp Mỡ", "20k", R.drawable.imgaesuonbi)
    )

    val difItems = listOf(
        Foodd(1, "Khăn Giấy", "10k", R.drawable.imgaesuonbi),
        Foodd(2, "Khăn lạnh", "15k", R.drawable.imgaesuonbi)
    )

    val combinedItems = listOf(
        "Món Chính" to mainItems,
        "Món Phụ" to sideItems,
        "Topping" to toppingItems,
        "Khác" to difItems
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff252121))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 7.dp)
                    .background(Color(0xff252121))
            ) {
                combinedItems.forEach { (title, items) ->
                    item {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White,
                            modifier = Modifier.padding(start = 20.dp, top = 20.dp)
                        )
                    }
                    itemsIndexed(items) { index, item ->
                        FoodItem(index + 1, item)
                    }
                    item {
                        Divider(
                            thickness = 3.dp,
                            color = Color.White,
                            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                        )
                    }
                }
                item {
                    Column(modifier = Modifier.padding(start = 10.dp)) {
                        SummaryRow("Số lượng món chính :", "4")
                        SummaryRow("Số lượng món thêm :", "4")
                        SummaryRow("Số lượng topping :", "2")
                        SummaryRow("Số lượng món khác :", "3")
                        SummaryRow("Tổng số lượng :", "13")
                    }
                    Divider(
                        thickness = 3.dp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )
                    Column(modifier = Modifier.padding(start = 10.dp, top = 16.dp)) {
                        Row(
                            modifier = Modifier
                                .padding(3.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Tổng tiền :",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White,
                                modifier = Modifier.padding(10.dp)
                            )
                            Text(
                                text = "140K",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .width(80.dp)
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(0.dp)
                            .background(color = Color.Black)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = { /* Reset action */ },
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .height(40.dp)
                                .width(150.dp),
                            colors = ButtonDefaults.buttonColors(Color(0XFFFE724C)),
                            shape = RoundedCornerShape(12.dp),
                        ) {
                            Text(text = "RESET", style = MaterialTheme.typography.titleMedium)
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Button(
                            onClick = { /* Buy action */ },
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .height(40.dp)
                                .width(150.dp),
                            colors = ButtonDefaults.buttonColors(Color(0XFFFE724C)),
                            shape = RoundedCornerShape(12.dp),
                        ) {
                            Text(text = "MUA", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FoodItem(order: Int, item: Foodd) {
    var soLuong = remember { mutableIntStateOf(1) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .padding(5.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2F2D2D))
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
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
            Column(modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterVertically)) {
                TangGiamSoLuong(soLuong)
            }
        }
    }
}

@Composable
fun SummaryRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier
                .padding(10.dp)
                .width(40.dp)
        )
    }
}

@Composable
fun TangGiamSoLuong(soLuong: MutableIntState) {
    val context = LocalContext.current
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
                    Toast.makeText(context, "Số lượng không thể nhỏ hơn 1", Toast.LENGTH_SHORT)
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
        Text(text = "${soLuong.value}", color = Color.White, fontSize = 20.sp)
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
@Preview(showBackground = true)
@Composable
fun CartNoOrders() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xff252121)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_cart),
            contentDescription = "Shopping Cart Icon",
            modifier = Modifier.size(180.dp)
        )
        Spacer(modifier = Modifier.height(26.dp))
        Text(
            text = "Không có đơn hàng",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
        )
        Spacer(modifier = Modifier.height(26.dp))
        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .background(
                    color = Color.White,
                    RoundedCornerShape(22.dp)
                )
        ) {
            Text(
                text = "    Đặt hàng    ",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                )
            )
        }
        Spacer(modifier = Modifier.height(26.dp))
    }
}

@Composable
fun GetRowTTItem(pay: Pay) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = pay.color, shape = RoundedCornerShape(12.dp))
            .height(70.dp)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = pay.image),
            contentDescription = "",
            modifier = Modifier.size(60.dp)
        )

        Text(
            text = pay.name,
            modifier = Modifier
                .weight(1f)
                .padding(10.dp, 0.dp, 0.dp, 0.dp),
            color = Color.White,
            fontSize = 20.sp
        )

        RadioButton(selected = false, onClick = {})
    }
}
@Composable
private fun GetAddress() {
    Text(
        text = "địa chỉ nhận hàng",
        fontSize = 16.sp,
        color = Color.White,
        modifier = Modifier.fillMaxWidth(),
    )
    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .padding(top = 8.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_adress),
            contentDescription = "Icon Address",
            tint = Color.White,
        )
        Column(modifier = Modifier.fillMaxWidth().padding(5.dp)) {
            Text(
                text = "Nam nassnkcsnoccom",
                fontSize = 16.sp,
                color = Color.White,
            )
            Text(
                modifier = Modifier.width(250.dp),
                text = "Trịnh Văn Bô Phương Canh Nam Từ Liêm Hà Nội",
                fontSize = 16.sp,
                color = Color.White,
            )
        }
    }
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Chọn thanh toán sau:",
        fontSize = 16.sp,
        color = Color.White,
    )
}
data class Pay(val id: Int, val color: Color, val image: Int, val name: String)
@Preview(showBackground = true)
@Composable
private fun GetLayout(
    title: String = "Thanh Toán",
    innerPadding: PaddingValues = PaddingValues(0.dp)
) {
    val payItems = listOf(
        Pay(1, Color(0XFFEB8B33), R.drawable.ic_logo_paypal, "PayPal"),
        Pay(2, Color(0XFF57D100), R.drawable.ic_logo_visa, "VISA"),
        Pay(3, Color(0XFFEC1387), R.drawable.ic_logo_momo, "Momo"),
        Pay(4, Color(0XFF03C2FC), R.drawable.ic_logo_zalopay, "Zalo Pay"),
        Pay(4, Color(0XFF13ECEA), R.drawable.ic_logo_tructiep, "Thanh toán trực tiếp")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color("#363232".toColorInt()))
            .padding(
                top = innerPadding.calculateTopPadding() + 10.dp,
                start = 10.dp,
                end = 10.dp,
                bottom = 10.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        GetTextTitle(title, innerPadding)
        GetAddress()
        LazyColumn(
            modifier = Modifier
                .padding(top = 7.dp)
                .background(Color(0xff252121)),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(payItems) { item ->
                GetRowTTItem(item)
            }
        }
        Button(
            onClick = { /* Buy action */ },
            modifier = Modifier
                .padding(bottom = 10.dp)
                .height(40.dp)
                .width(150.dp),
            colors = ButtonDefaults.buttonColors(Color(0XFFFE724C)),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(text = "Tiếp theo", style = MaterialTheme.typography.titleMedium)
        }
    }
}
@Composable
private fun GetTextTitle(
    title: String = "Thanh Toán",
    innerPadding: PaddingValues = PaddingValues(0.dp)
) {
    Text(
        text = title,
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth(),
        fontSize = 30.sp,
        color = Color.White
    )
}
@Preview(showBackground = true)
@Composable
fun PreviewCartUserScreen() {
    CartUserScreen()
}
