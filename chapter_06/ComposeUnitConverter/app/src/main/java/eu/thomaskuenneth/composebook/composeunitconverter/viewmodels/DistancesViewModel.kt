package eu.thomaskuenneth.composebook.composeunitconverter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.thomaskuenneth.composebook.composeunitconverter.R
import eu.thomaskuenneth.composebook.composeunitconverter.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DistancesViewModel(private val repository: Repository) : ViewModel() {

    private val _unit: MutableStateFlow<Int> = MutableStateFlow(
        repository.getInt("unit", R.string.meter)
    )

    val unit: StateFlow<Int>
        get() = _unit

    fun setUnit(value: Int) {
        _unit.value = value
        repository.putInt("unit", value)
    }

    private val _distance: MutableStateFlow<String> = MutableStateFlow(
        repository.getString("distance", "")
    )

    val distance: StateFlow<String>
        get() = _distance

    fun getDistanceAsFloat(): Float = _distance.value.let {
        return try {
            it.toFloat()
        } catch (e: NumberFormatException) {
            Float.NaN
        }
    }

    fun setDistance(value: String) {
        _distance.value = value
        repository.putString("distance", value)
    }

    private val _convertedDistance: MutableStateFlow<Float> = MutableStateFlow(Float.NaN)

    val convertedDistance: StateFlow<Float>
        get() = _convertedDistance

    fun convert() {
        getDistanceAsFloat().let {
            viewModelScope.launch {
                _convertedDistance.value = if (!it.isNaN())
                    if (_unit.value == R.string.meter)
                        it * 0.00062137F
                    else
                        it / 0.00062137F
                else
                    Float.NaN
            }
        }
    }
}
