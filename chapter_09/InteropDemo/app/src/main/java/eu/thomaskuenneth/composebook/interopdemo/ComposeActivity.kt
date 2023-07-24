package eu.thomaskuenneth.composebook.interopdemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import eu.thomaskuenneth.composebook.interopdemo.databinding.CustomBinding

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: InteropDemoViewModel by viewModels()
        viewModel.setSliderValue(intent.getFloatExtra(KEY, 0F))
        setContent {
            ViewIntegrationDemo(viewModel) {
                val i = Intent(
                    this,
                    ViewActivity::class.java
                )
                i.putExtra(KEY, viewModel.sliderValue.value)
                startActivity(i)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewIntegrationDemo(viewModel: InteropDemoViewModel, onClick: () -> Unit) {
    val sliderValueState = viewModel.sliderValue.collectAsStateWithLifecycle()
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title =
            {
                Text(text = stringResource(id = R.string.compose_activity))
            })
        }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Slider(
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    viewModel.setSliderValue(it)
                },
                value = sliderValueState.value
            )
            AndroidViewBinding(
                modifier = Modifier.fillMaxWidth(),
                factory = CustomBinding::inflate
            ) {
                textView.text = sliderValueState.value.toString()
                button.setOnClickListener {
                    onClick()
                }
            }
        }
    }
}
