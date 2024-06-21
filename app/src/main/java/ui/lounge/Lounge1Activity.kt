package ui.painting

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.galleryapp.R
import com.example.galleryapp.ui.theme.GalleryAppTheme
import ui.auth.RegisterViewModel
import ui.lounge.Lounge1ViewModel
import ui.lounge.PointLocation

data class Option(val name: String, val iconResId: Int)

class Lounge1Activity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleryAppTheme {
                Lounge1Screen()
            }
        }
    }
}
@Composable
fun Lounge1Screen(navController: NavController = rememberNavController(), lounge1ViewModel: Lounge1ViewModel = viewModel()) {
    Log.d("PaintingScreen", "PaintingScreen Composable called")

    val density = LocalDensity.current
    val wallThickness = with(density) { 3.dp.toPx() }
    val padding = with(density) { 20.dp.toPx() }

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawLounge1(this, wallThickness, padding)
    }
    OptionListPainting1()
}
fun drawLounge1(drawScope: DrawScope, wallThickness: Float, padding: Float) {
    val width = drawScope.size.width
    val totalHeight = drawScope.size.height
    val height = totalHeight * 0.6f // Use slightly less than three-quarters of the screen height
    val verticalOffset = (totalHeight - height) / 4 // Center vertically but move down a bit

    val roomWidth = (width - 3 * padding - wallThickness)
    val roomHeight = (height - 3 * padding - wallThickness) // Smaller rooms

    drawScope.drawRect(
        color = Color.Black,
        topLeft = Offset(padding, verticalOffset + padding),
        size = androidx.compose.ui.geometry.Size(roomWidth, roomHeight),
        style = Stroke(width = wallThickness)
    )
    drawScope.drawRect(
        color = Color.Green,
        topLeft = Offset(
            padding + roomWidth - wallThickness / 2,
            verticalOffset + padding + roomHeight / 2 - wallThickness / 2
        ),
        size = androidx.compose.ui.geometry.Size(wallThickness, roomHeight / 2)
    )

}
@Composable
fun drawPictures() {

}

@Composable
fun OptionListPainting1() {
    val options = listOf(
        Option("INICIO", R.drawable.ic_launcher_foreground),
        Option("Pintura 1", R.drawable.img1),
        Option("Pintura 2", R.drawable.img2),
        Option("Pintura 3", R.drawable.img3)
    )
    var selectedOption by remember { mutableStateOf(options[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Seleccionado: ${selectedOption.name}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(options) { option ->
                OptionItem(option, selectedOption == option) {
                    selectedOption = option
                }
            }
        }
    }
}
@Composable
fun OptionItem(option: Option, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) Color.Gray else Color.Transparent
    //val textColor = if (isSelected) Color.White else Color.Black

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(225.dp, 150.dp) // Aumentar el tamaño de las opciones
            .background(backgroundColor)
            .clickable { onClick() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = option.iconResId),
                contentDescription = option.name,
                modifier = Modifier.size(215.dp, 105.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = option.name,
                //color = textColor,
                fontSize = 18.sp, // Aumentar el tamaño de la fuente
                fontWeight = FontWeight.Bold
            )
        }
    }
}