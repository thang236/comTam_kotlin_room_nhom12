package com.example.comtam_kotlin_room.ui.screen.home
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.comtam_kotlin_room.data.entity.OderCart
import com.example.comtam_kotlin_room.utils.Route
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class ListItem(val title: String, val description: String, val price:String)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navigationController: NavHostController
               ,orderCartViewModel: OderCartViewModel
) {
    val allOrders by orderCartViewModel.allOders.collectAsState(initial = emptyList())
    val currentDate = LocalDate.now()
    val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
    val totalRevenue = allOrders.sumOf { it.total.toInt() }

    // Sử dụng LazyColumn để hiển thị danh sách


      Column(  modifier = Modifier
          .fillMaxSize()
          .background(Color(0xff252121)),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center

      ) {

              Text(
                  text = "Today: ${formattedDate.toString()}",
                  style = MaterialTheme.typography.titleMedium,
                  color = Color.White,
                  modifier = Modifier.padding(top = 16.dp)

              )

              Text(
                  text = "Số lượng đơn: ${allOrders.size}",
                  style = MaterialTheme.typography.titleMedium,
                  color = Color.White,
              )


              Text(
                  text = "Doanh thu: ${totalRevenue}k",
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
              items(allOrders) { item ->
                  ListItemView(item = item, navigationController = navigationController, orderCartViewModel = orderCartViewModel)
              }
          }
      }


}

@Composable
fun ListItemView(item: OderCart,
                 navigationController: NavHostController,
                 orderCartViewModel: OderCartViewModel) {

    val textColor = when (item.status) {
        0 -> Color.Yellow // Màu chữ cho trạng thái "Chưa xác nhận"
        1 -> Color.Red // Màu chữ cho trạng thái "Từ chối"
        2 -> Color.Green // Màu chữ cho trạng thái "Xác nhận"
        else -> Color.Gray // Màu chữ mặc định cho trạng thái không xác định
    }
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(top = 10.dp)

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
                    text = "Đơn hàng:",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                )
                Text(
                    text = "${item.idUser}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier.padding(end = 140.dp)
                )
                Text(
                    text = "||",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .clickable {
                            orderCartViewModel.idOrder.value = item.idCart
                            navigationController.navigate(Route.DetailCart.screen)
                        }
                )
                Text(
                    text = "${item.total}k",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFFFF8C00),
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
                    text = "${statusToString(item.status)}",
                    style = MaterialTheme.typography.titleMedium,
                    color = textColor,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
        }
    }
    }
}
@Composable
fun statusToString(status: Int): String {
    return when (status) {
        0 -> "Chưa xác nhận"
        1 -> "Từ chối"
        2 -> "Xác nhận"
        else -> "Unknown"
    }
}



