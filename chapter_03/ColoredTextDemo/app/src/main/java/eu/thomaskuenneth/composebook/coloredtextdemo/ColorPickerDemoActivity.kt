package eu.thomaskuenneth.composebook.coloredtextdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle

class ColorPickerDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                println(
                    ColoredTextDemo(
                        text = stringResource(id = R.string.colored_text_demo),
                        color = Color.Blue
                    )
                )
                ShortColoredTextDemo(
                    text = stringResource(id = R.string.short_colored_text_demo),
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun ColoredTextDemo(
    text: String = "",
    color: Color = Color.Black
) {
    Text(
        text = text,
        style = TextStyle(color = color)
    )
}

@Composable
fun ShortColoredTextDemo(
    text: String = "",
    color: Color = Color.Black
) = Text(
    text = text,
    style = TextStyle(color = color)
)
