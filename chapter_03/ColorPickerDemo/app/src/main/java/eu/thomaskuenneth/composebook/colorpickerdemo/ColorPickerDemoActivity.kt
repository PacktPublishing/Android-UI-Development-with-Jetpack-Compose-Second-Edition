package eu.thomaskuenneth.composebook.colorpickerdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min

class ColorPickerDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoxWithConstraints(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            ) {
                // first version
//                Column(
//                    modifier = Modifier.width(min(400.dp, maxWidth)),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    val color = remember { mutableStateOf(Color.Magenta) }
//                    ColorPicker(color)
//                    Text(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(color.value),
//                        text = "#${color.value.toArgb().toUInt().toString(16)}",
//                        textAlign = TextAlign.Center,
//                        style = MaterialTheme.typography.displayMedium.merge(
//                            TextStyle(
//                                color = color.value.complementary()
//                            )
//                        )
//                    )
//                }

                // second version
                Column(
                    modifier = Modifier.width(min(400.dp, maxWidth)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var color by remember { mutableStateOf(Color.Magenta) }
                    ColorPicker(
                        color = color,
                        colorChanged = { color = it }
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color),
                        text = "#${color.toArgb().toUInt().toString(16)}",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.displayMedium.merge(
                            TextStyle(
                                color = color.complementary()
                            )
                        )
                    )
                }

            }
        }
    }
}

// first version
@Composable
fun ColorPicker(color: MutableState<Color>) {
    val red = color.value.red
    val green = color.value.green
    val blue = color.value.blue
    Column {
        Slider(
            value = red,
            onValueChange = {
                color.value = Color(
                    it, green,
                    blue
                )
            })
        Slider(
            value = green,
            onValueChange = { color.value = Color(red, it, blue) })
        Slider(
            value = blue,
            onValueChange = { color.value = Color(red, green, it) })
    }
}

// second version
@Composable
fun ColorPicker(color: Color, colorChanged: (Color) -> Unit) {
    val red = color.red
    val green = color.green
    val blue = color.blue
    Column {
        Slider(
            value = red,
            onValueChange = { colorChanged(Color(it, green, blue)) })
        Slider(
            value = green,
            onValueChange = { colorChanged(Color(red, it, blue)) })
        Slider(
            value = blue,
            onValueChange = { colorChanged(Color(red, green, it)) })
    }
}

fun Color.complementary() = Color(
    red = 1F - red,
    green = 1F - green,
    blue = 1F - blue
)
