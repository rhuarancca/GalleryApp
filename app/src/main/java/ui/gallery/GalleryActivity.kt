package ui.gallery

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.galleryapp.R
import com.example.galleryapp.ui.theme.GalleryAppTheme
import ui.painting.Option


class GalleryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                GalleryAppTheme {
                    GalleryScreen()
            }
        }
    }
}

@Composable
fun GalleryScreen(navController: NavController = rememberNavController(), galleryViewModel: GalleryViewModel = viewModel()) {
    val density = LocalDensity.current
    val wallThickness = with(density) { 2.dp.toPx() }
    val padding = with(density) { 20.dp.toPx() }
    val pathThickness = with(density) { 4.dp.toPx() }
    val arrowSize = with(density) { 10.dp.toPx() }

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawGallery(this, wallThickness, padding)
        drawPath(this, wallThickness, padding, pathThickness, arrowSize, 1)
    }
    OptionListLounge()
}

fun drawGallery(drawScope: DrawScope, wallThickness: Float, padding: Float) {
    val width = drawScope.size.width
    val totalHeight = drawScope.size.height
    val height = totalHeight * 0.6f // Use slightly less than three-quarters of the screen height
    val verticalOffset = (totalHeight - height) / 4 // Center vertically but move down a bit

    val roomWidth = (width - 3 * padding - wallThickness) / 2
    val roomHeight = (height - 3 * padding - wallThickness) / 3  // Smaller rooms

    // Draw rooms with entrances
    // Room 1 with entrance on the right
    drawScope.drawRect(
        color = Color.Black,
        topLeft = Offset(padding, verticalOffset + padding),
        size = androidx.compose.ui.geometry.Size(roomWidth, roomHeight),
        style = Stroke(width = wallThickness)
    )
    drawScope.drawRect(
        color = Color.Green,
        topLeft = Offset(padding + roomWidth - wallThickness / 2, verticalOffset + padding + roomHeight / 2 - wallThickness / 2),
        size = androidx.compose.ui.geometry.Size(wallThickness, roomHeight / 2)
    )

    // Room 2 with entrance on the left
    drawScope.drawRect(
        color = Color.Black,
        topLeft = Offset(roomWidth + 2 * padding, verticalOffset + padding),
        size = androidx.compose.ui.geometry.Size(roomWidth, roomHeight),
        style = Stroke(width = wallThickness)
    )
    drawScope.drawRect(
        color = Color.Green,
        topLeft = Offset(roomWidth + 2 * padding - wallThickness / 2, verticalOffset + padding + roomHeight / 2 - wallThickness / 2),
        size = androidx.compose.ui.geometry.Size(wallThickness, roomHeight / 2)
    )

    // Room 3 with entrance on the right
    drawScope.drawRect(
        color = Color.Black,
        topLeft = Offset(padding, verticalOffset + roomHeight + 2 * padding),
        size = androidx.compose.ui.geometry.Size(roomWidth, roomHeight),
        style = Stroke(width = wallThickness)
    )
    drawScope.drawRect(
        color = Color.Green,
        topLeft = Offset(padding + roomWidth - wallThickness / 2, verticalOffset + roomHeight + 2 * padding + roomHeight / 2 - wallThickness / 2),
        size = androidx.compose.ui.geometry.Size(wallThickness, roomHeight / 2)
    )

    // Room 4 with entrance on the left
    drawScope.drawRect(
        color = Color.Black,
        topLeft = Offset(roomWidth + 2 * padding, verticalOffset + roomHeight + 2 * padding),
        size = androidx.compose.ui.geometry.Size(roomWidth, roomHeight),
        style = Stroke(width = wallThickness)
    )
    drawScope.drawRect(
        color = Color.Green,
        topLeft = Offset(roomWidth + 2 * padding - wallThickness / 2, verticalOffset + roomHeight + 2 * padding + roomHeight / 2 - wallThickness / 2),
        size = androidx.compose.ui.geometry.Size(wallThickness, roomHeight / 2)
    )

    // Draw the outer boundary
    val outerBoundaryLeft = padding / 2
    val outerBoundaryTop = verticalOffset + padding / 2
    val outerBoundaryWidth = width - padding
    val outerBoundaryHeight = height - padding

    drawScope.drawRect(
        color = Color.Black,
        topLeft = Offset(outerBoundaryLeft, outerBoundaryTop),
        size = androidx.compose.ui.geometry.Size(outerBoundaryWidth, outerBoundaryHeight),
        style = Stroke(width = wallThickness)
    )

    // Draw entrance in the outer boundary
    val entranceWidth = roomWidth / 2
    drawScope.drawRect(
        color = Color.Green,
        topLeft = Offset((width - entranceWidth) / 2, outerBoundaryTop + outerBoundaryHeight - wallThickness / 2),
        size = androidx.compose.ui.geometry.Size(entranceWidth, wallThickness)
    )


}
fun drawPath(drawScope: DrawScope, wallThickness: Float, padding: Float, pathThickness: Float, arrowSize: Float, loungeCurrent: Int) {
    val width = drawScope.size.width
    val totalHeight = drawScope.size.height
    val height = totalHeight * 0.6f // Use slightly less than three-quarters of the screen height
    val verticalOffset = (totalHeight - height) / 4 // Center vertically but move down a bit


    val roomWidth = (width - 3 * padding - wallThickness) / 2
    val roomHeight = (height - 3 * padding - wallThickness) / 3  // Smaller rooms
    val entranceWidth = roomWidth / 2
    // Draw the outer boundary
    val outerBoundaryTop = verticalOffset + padding / 2
    val outerBoundaryHeight = height - padding

    var pathStartX = (width - entranceWidth) / 2 + entranceWidth / 2
    var pathStartY = outerBoundaryTop + outerBoundaryHeight
    var pathTurnX = width / 2
    var pathTurnY = verticalOffset + padding + roomHeight / 2
    var pathEndX = padding + roomWidth - 2 * wallThickness
    var pathEndY = verticalOffset + padding + roomHeight / 2

    if (loungeCurrent == 2){
        pathTurnX = width / 3
        pathTurnY = verticalOffset + padding + roomHeight / 2
        pathEndX = padding + roomWidth - 2 * wallThickness
        pathEndY = verticalOffset + padding + roomHeight / 2
    }
    // Draw a path from the entrance to the entrance of room 1


    // Draw the path
    drawScope.drawLine(
        color = Color.Red,
        start = Offset(pathStartX, pathStartY),
        end = Offset(pathTurnX, pathTurnY),
        strokeWidth = pathThickness
    )

    drawScope.drawLine(
        color = Color.Red,
        start = Offset(pathTurnX, pathTurnY),
        end = Offset(pathEndX, pathEndY),
        strokeWidth = pathThickness
    )

    // Draw arrowhead
    val arrowPath = Path().apply {
        moveTo(pathEndX, pathEndY)
        lineTo(pathEndX - arrowSize, pathEndY - arrowSize / 2)
        lineTo(pathEndX - arrowSize, pathEndY + arrowSize / 2)
        close()
    }
    drawScope.drawPath(
        path = arrowPath,
        color = Color.Red
    )
    drawScope.drawCircle(
        color = Color.Black,
        radius = roomWidth/10
    )
}

@Composable
fun OptionListLounge() {
    val options = listOf(
        Option("INICIO", R.drawable.ic_launcher_foreground),
        Option("Area 1", R.drawable.img1),
        Option("Area 2", R.drawable.img2),
        Option("Area 3", R.drawable.img3)
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
            .clickable { onClick(

            ) }
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

@Composable
fun OptionListPictures() {
    val optionsPictures = listOf(
        Option("INICIO", R.drawable.ic_launcher_foreground),
        Option("Area 1", R.drawable.img1),
        Option("Area 2", R.drawable.img2),
        Option("Area 3", R.drawable.img3)
    )
    var selectedOption by remember { mutableStateOf(optionsPictures[0]) }

}
