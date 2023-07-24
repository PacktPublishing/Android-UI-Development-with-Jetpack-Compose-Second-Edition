package eu.thomaskuenneth.composebook.interopdemo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class InteropDemoViewModel : ViewModel() {

    private val _sliderValue: MutableStateFlow<Float> =
        MutableStateFlow(0.5F)

    val sliderValue: StateFlow<Float>
        get() = _sliderValue

    fun setSliderValue(value: Float) {
        _sliderValue.value = value
    }
}
