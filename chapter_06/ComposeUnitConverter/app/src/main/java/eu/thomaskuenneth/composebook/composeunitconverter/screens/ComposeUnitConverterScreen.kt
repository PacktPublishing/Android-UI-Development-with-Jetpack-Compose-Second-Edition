package eu.thomaskuenneth.composebook.composeunitconverter.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SquareFoot
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.ui.graphics.vector.ImageVector
import eu.thomaskuenneth.composebook.composeunitconverter.R

sealed class ComposeUnitConverterScreen(
    val route: String,
    @StringRes val label: Int,
    val icon: ImageVector
) {
    companion object {
        val screens = listOf(
            Temperature,
            Distances
        )

        const val route_temperature = "temperature"
        const val route_distances = "distances"
    }

    private object Temperature : ComposeUnitConverterScreen(
        route_temperature,
        R.string.temperature,
        Icons.Default.Thermostat
    )

    private object Distances : ComposeUnitConverterScreen(
        route_distances,
        R.string.distances,
        Icons.Default.SquareFoot
    )
}
