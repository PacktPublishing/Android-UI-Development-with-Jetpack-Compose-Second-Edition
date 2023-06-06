package eu.thomaskuenneth.composebook.modifierdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class ModifierDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5F)
                ) {
                    OrderDemo()
                }
                TextWithYellowBackground(
                    text = "Hello Compose",
                    modifier = Modifier.padding(32.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5F)
                ) {
                    TextWithYellowCross()
                }
            }
        }
    }
}

@Composable
@Preview(widthDp = 400, heightDp = 200)
fun TextWithYellowCross() {
    Text(
        text = "Hello Compose",
        modifier = Modifier
            .fillMaxSize()
            .drawYellowCross(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineLarge
    )
}

@Composable
fun OrderDemo() {
    var color by remember { mutableStateOf(Color.Blue) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .border(BorderStroke(width = 2.dp, color = color))
            .background(Color.LightGray)
            .clickable {
                color = if (color == Color.Blue)
                    Color.Red
                else
                    Color.Blue
            }
    )
}

@Composable
fun TextWithYellowBackground(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier.background(Color.Yellow)
    )
}

fun Modifier.drawYellowCross() = then(
    object : DrawModifier {
        override fun ContentDrawScope.draw() {
            drawLine(
                color = Color.Yellow,
                start = Offset(0F, 0F),
                end = Offset(size.width - 1, size.height - 1),
                strokeWidth = 10F
            )
            drawLine(
                color = Color.Yellow,
                start = Offset(0F, size.height - 1),
                end = Offset(size.width - 1, 0F),
                strokeWidth = 10F
            )
            drawContent()
        }
    }
)
