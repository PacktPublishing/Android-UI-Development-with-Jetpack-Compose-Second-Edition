package eu.thomaskuenneth.composebook.testinganddebuggingdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

fun isEven(num: Int): Boolean {
    val div2 = num / 2
    return (div2 * 2) == num
}

class TestingAndDebuggingDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleButtonDemo()
//            BoxButtonDemo()
        }
    }
}

@Composable
fun SimpleButtonDemo() {
    val a = stringResource(id = R.string.a)
    val b = stringResource(id = R.string.b)
    var text by remember { mutableStateOf(a) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            text = if (text == a) b else a
        }) {
            Text(text = text)
        }
    }
}

@Composable
fun ImageDemo() {
    Image(
        painter = painterResource(id = R.drawable.ic_baseline_airport_shuttle_24),
        contentDescription = stringResource(id = R.string.airport_shuttle),
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .size(width = 128.dp, height = 128.dp)
            .background(Color.Blue)
    )
}

val BackgroundColorKey = SemanticsPropertyKey<Color>("BackgroundColor")
var SemanticsPropertyReceiver.backgroundColor by BackgroundColorKey

val COLOR1 = Color.White
val COLOR2 = Color.LightGray
const val TAG1 = "BoxButtonDemo"

@Composable
fun BoxButtonDemo() {
    var color by remember { mutableStateOf(COLOR1) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag(TAG1)
            .simpleDebug()
            .semantics { backgroundColor = color }
            .background(color = color),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                color = if (color == COLOR1)
                    COLOR2
                else
                    COLOR1
            }) {
            Text(text = stringResource(id = R.string.toggle))
        }
    }
}

fun Modifier.simpleDebug() = then(object : DrawModifier {
    override fun ContentDrawScope.draw() {
        println("width=${size.width}, height=${size.height}")
        drawContent()
    }
})
