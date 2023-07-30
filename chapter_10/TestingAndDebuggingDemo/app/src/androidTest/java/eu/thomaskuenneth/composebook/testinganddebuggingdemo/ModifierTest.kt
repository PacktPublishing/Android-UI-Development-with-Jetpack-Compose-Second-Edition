import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.InspectableValue
import androidx.compose.ui.platform.isDebugInspectorInfoEnabled
import androidx.compose.ui.semantics.semantics
import androidx.test.ext.junit.runners.AndroidJUnit4
import eu.thomaskuenneth.composebook.testinganddebuggingdemo.backgroundColor
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ModifierTest {

    @Before
    fun setup() {
        isDebugInspectorInfoEnabled = true
    }

    @After
    fun teardown() {
        isDebugInspectorInfoEnabled = false
    }

    @Test
    fun testModifierInspectableValue() {
        val modifier = Modifier.semantics { backgroundColor = Color.White }
        assertEquals(modifier.foldIn(0) { i, _ -> i + 1 }, 1)
        with(modifier as InspectableValue) {
            assertEquals(nameFallback, "semantics")
        }
    }
}
