@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package eu.thomaskuenneth.composebook.windowsizeclassdemo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

val LocalWindowSizeClass = compositionLocalOf { WindowSizeClass.calculateFromSize(DpSize.Zero) }

class WindowSizeClassDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = defaultColorScheme()
            ) {
                CompositionLocalProvider(
                    LocalWindowSizeClass provides calculateWindowSizeClass(activity = this)
                ) {
                    WindowSizeClassDemoScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WindowSizeClassDemoScreen() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                }, scrollBehavior = scrollBehavior
            )
        }) {
        val list = (1..42).toList()
//        SimpleScreen(
//            paddingValues = it,
//            list = list
//        )
        AdaptiveScreen(
            paddingValues = it,
            list = list
        )
    }
}

@Composable
fun SimpleScreen(
    paddingValues: PaddingValues,
    list: List<Int>
) {
    NumbersList(
        paddingValues = paddingValues,
        list = list
    )
}

@Composable
fun NumbersList(
    paddingValues: PaddingValues, list: List<Int>
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues = paddingValues),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = list
        ) {
            NumbersItem(it)
        }
    }
}

@Composable
private fun NumbersItem(number: Int) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.primary),
        text = number.toString(),
        style = MaterialTheme.typography.displayLarge,
        textAlign = TextAlign.Center
    )
}

@Composable
fun defaultColorScheme() = with(isSystemInDarkTheme()) {
    val hasDynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val context = LocalContext.current
    when (this) {
        true -> if (hasDynamicColor) {
            dynamicDarkColorScheme(context)
        } else {
            darkColorScheme()
        }

        false -> if (hasDynamicColor) {
            dynamicLightColorScheme(context)
        } else {
            lightColorScheme()
        }
    }
}

@Composable
fun NumbersGrid(
    paddingValues: PaddingValues,
    list: List<Int>,
    columns: Int = 2
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier.padding(paddingValues = paddingValues),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = list
        ) {
            NumbersItem(number = it)
        }
    }
}

@Composable
fun AdaptiveScreen(
    paddingValues: PaddingValues,
    list: List<Int>
) {
    with(LocalWindowSizeClass.current) {
        if (widthSizeClass == WindowWidthSizeClass.Compact) {
            NumbersList(
                paddingValues = paddingValues,
                list = list
            )
        } else {
            NumbersGrid(
                paddingValues = paddingValues,
                list = list,
                columns = when (widthSizeClass) {
                    WindowWidthSizeClass.Medium -> 2
                    else -> 3
                }
            )
        }
    }
}
