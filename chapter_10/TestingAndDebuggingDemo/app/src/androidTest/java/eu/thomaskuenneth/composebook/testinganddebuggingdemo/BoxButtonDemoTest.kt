package eu.thomaskuenneth.composebook.testinganddebuggingdemo

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BoxButtonDemoTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testHasTestTag() {
        rule.setContent {
            BoxButtonDemo()
        }
        rule.onNode(hasTestTag(TAG1))
            .assertExists()
    }

    @Test
    fun testBoxInitialBackgroundColorIsColor1() {
        rule.setContent {
            BoxButtonDemo()
        }
        rule.onNode(SemanticsMatcher.expectValue(BackgroundColorKey, COLOR1)).assertExists()
    }
}
