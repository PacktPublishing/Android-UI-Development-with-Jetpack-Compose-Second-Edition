package eu.thomaskuenneth.composebook.predefinedlayoutsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class PredefinedLayoutsDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PredefinedLayoutsDemo()
        }
    }
}

@Composable
@Preview
fun PredefinedLayoutsDemo() {
    var red by remember { mutableStateOf(true) }
    var green by remember { mutableStateOf(true) }
    var blue by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CheckboxWithLabel(
            label = stringResource(id = R.string.red),
            checked = red,
            onClicked = { red = it }
        )
        CheckboxWithLabel(
            label = stringResource(id = R.string.green),
            checked = green,
            onClicked = { green = it }
        )
        CheckboxWithLabel(
            label = stringResource(id = R.string.blue),
            checked = blue,
            onClicked = { blue = it }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            if (red) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red)
                )
            }
            if (green) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                        .background(Color.Green)
                )
            }
            if (blue) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(64.dp)
                        .background(Color.Blue)
                )
            }
        }
    }
}

@Composable
fun CheckboxWithLabel(
    label: String,
    checked: Boolean,
    onClicked: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            onClicked(!checked)
        }, verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = {
                onClicked(it)
            }
        )
        Text(
            text = label,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
