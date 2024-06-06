package com.example.comtam_kotlin_room.ui.screen.home
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.comtam_kotlin_room.utils.Route

data class ListItem(val title: String, val description: String, val price:String)

@Composable
fun HomeScreen(navigationController: NavHostController) {

    // Danh sách các mục để hiển thị
    val itemsList = listOf(
        ListItem("Đơn hàng CT2E22E", "Từ chối", "100.000 đ"),
        ListItem("Đơn hàng CT2E2206", "Chấp nhận", "500.000 đ"),
        ListItem("Đơn hàng CT2E23E", "Từ chối", "100.800 đ"),
        ListItem("Đơn hàng CT2E12E", "Từ chối", "101.854 đ"),
        ListItem("Đơn hàng CT2E12E", "Từ chối", "101.854 đ"),
        ListItem("Đơn hàng CT2E12E", "Từ chối", "101.854 đ"),
        ListItem("Đơn hàng CT2E12E", "Từ chối", "101.854 đ"),
        ListItem("Đơn hàng CT2E12E", "Từ chối", "101.854 đ"),
        ListItem("Đơn hàng CT2E12E", "Từ chối", "101.854 đ"),
        ListItem("Đơn hàng CT2E12E", "Từ chối", "101.854 đ"),

        )

    // Sử dụng LazyColumn để hiển thị danh sách


      Column(  modifier = Modifier.
      fillMaxSize().
      background(Color(0xff252121)),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center

      ) {

              Text(
                  text = "Today:23-09-2024 ",
                  style = MaterialTheme.typography.titleMedium,
                  color = Color.White,
                  modifier = Modifier.padding(top = 16.dp)

              )

              Text(
                  text = "Số lượng đơn: 1",
                  style = MaterialTheme.typography.titleMedium,
                  color = Color.White,
              )


              Text(
                  text = "Doanh thu: 500.000 đ",
                  style = MaterialTheme.typography.titleMedium,
                  color = Color.White,
              )


          LazyColumn(
              modifier = Modifier
                  .padding(top = 7.dp, start = 16.dp, end = 16.dp)
                  .fillMaxSize()
                  .background(Color(0xff252121))
          )
          {
              items(itemsList) { item ->
                  ListItemView(item = item, navigationController = navigationController)
              }
          }
      }


}

@Composable
fun ListItemView(item: ListItem,navigationController: NavHostController) {

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding( top = 10.dp)

            .height(110.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) ,
//        colors = CardDefaults.cardColors(containerColor = Color(0x2F2D2D))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray) // Màu nền của Card

        ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                )
                Text(
                    text = "||",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .clickable {
                          navigationController.navigate(Route.DetailCart.screen)
                        }
                )
                Text(
                    text = item.price,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier.padding(end = 10.dp, bottom = 10.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Trạng Thái",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Red,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
        }
    }
    }
}
@Composable

fun HomeScreenPreview() {
    HomeScreen(navigationController = rememberNavController())
}

