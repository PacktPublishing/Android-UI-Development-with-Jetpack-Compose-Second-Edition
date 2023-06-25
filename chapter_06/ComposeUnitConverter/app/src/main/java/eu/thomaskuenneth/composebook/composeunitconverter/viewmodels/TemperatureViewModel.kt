package eu.thomaskuenneth.composebook.composeunitconverter.viewmodels

import androidx.lifecycle.ViewModel
import eu.thomaskuenneth.composebook.composeunitconverter.R
import eu.thomaskuenneth.composebook.composeunitconverter.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TemperatureViewModel(private val repository: Repository) : ViewModel() {

    private val _scale: MutableStateFlow<Int> = MutableStateFlow(
        repository.getInt("scale", R.string.celsius)
    )

    val scale: StateFlow<Int>
        get() = _scale

    fun setScale(value: Int) {
        _scale.value = value
        repository.putInt("scale", value)
    }

    private val _temperature: MutableStateFlow<String> = MutableStateFlow(
        repository.getString("temperature", "")
    )

    val temperature: StateFlow<String>
        get() = _temperature

    fun getTemperatureAsFloat(): Float = _temperature.value.let {
        return try {
            it.toFloat()
        } catch (e: NumberFormatException) {
            Float.NaN
        }
    }

    fun setTemperature(value: String) {
        _temperature.value = value
        repository.putString("temperature", value)
    }

    fun convert() = getTemperatureAsFloat().let {
        if (!it.isNaN())
            if (_scale.value == R.string.celsius)
                (it * 1.8F) + 32F
            else
                (it - 32F) / 1.8F
        else
            Float.NaN
    }
}
