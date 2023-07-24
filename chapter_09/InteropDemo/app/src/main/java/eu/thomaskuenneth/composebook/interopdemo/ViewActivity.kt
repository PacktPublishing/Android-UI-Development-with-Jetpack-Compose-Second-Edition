package eu.thomaskuenneth.composebook.interopdemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import eu.thomaskuenneth.composebook.interopdemo.databinding.LayoutBinding
import kotlinx.coroutines.launch

const val KEY = "key"

class ViewActivity : AppCompatActivity() {

    private lateinit var binding: LayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: InteropDemoViewModel by viewModels()
        viewModel.setSliderValue(intent.getFloatExtra(KEY, 0F))
        binding = LayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sliderValue.collect {
                    binding.slider.value = it
                }
            }
        }
        binding.slider.addOnChangeListener { _, value, _ -> viewModel.setSliderValue(value) }
        binding.composeView.run {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnDetachedFromWindow)
            setContent {
                val sliderValue = viewModel.sliderValue.collectAsStateWithLifecycle()
                sliderValue.value.let {
                    ComposeDemo(it) {
                        val i = Intent(
                            context,
                            ComposeActivity::class.java
                        )
                        i.putExtra(KEY, it)
                        startActivity(i)
                    }
                }
            }
        }
    }
}

@Composable
fun ComposeDemo(value: Float, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .height(64.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value.toString()
            )
        }
        OutlinedButton(
            onClick = onClick,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.compose_activity)
            )
        }
    }
}
