package ui.painting

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalDensity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.galleryapp.ui.theme.GalleryAppTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip

class PaintingActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleryAppTheme {
                PaintingScreen()
            }
        }
    }
}
@Composable
fun PaintingScreen(navController: NavController = rememberNavController(), paintingViewModel: PaintingViewModel = viewModel()) {
    Log.d("PaintingScreen", "PaintingScreen Composable called")

    val density = LocalDensity.current
    val wallThickness = with(density) { 3.dp.toPx() }
    val padding = with(density) { 20.dp.toPx() }
    val pathThickness = with(density) { 4.dp.toPx() }
    val arrowSize = with(density) { 10.dp.toPx() }
    
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawLounge(this, wallThickness, padding)
    }
}
fun drawLounge(drawScope: DrawScope, wallThickness: Float, padding: Float) {
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