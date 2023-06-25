package eu.thomaskuenneth.composebook.composeunitconverter

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val AndroidGreen = Color(0xFF3DDC84)
val AndroidGreenDark = Color(0xFF20B261)
val Orange = Color(0xFFFFA500)
val OrangeDark = Color(0xFFCC8400)

private val DarkColorPalette = darkColorScheme(
    primary = AndroidGreenDark,
    secondary = OrangeDark,
)

private val LightColorPalette = lightColorScheme(
    primary = AndroidGreen,
    secondary = Orange,
)

@Composable
fun ComposeUnitConverterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colorScheme = colorScheme,
//        typography = Typography(button = TextStyle(fontSize = 24.sp)),
//        shapes = Shapes(small = CutCornerShape(8.dp)),
        content = content
    )
}

@Composable
@Preview
fun MaterialThemeDemo() {
    MaterialTheme(
        typography = Typography(
            headlineLarge = TextStyle(color = Color.Red)
        )
    ) {
        Row {
            Text(
                text = "Hello",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.width(2.dp))
            MaterialTheme(
                typography = Typography(
                    headlineLarge = TextStyle(color = Color.Blue)
                )
            ) {
                Text(
                    text = "Compose",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }
}
