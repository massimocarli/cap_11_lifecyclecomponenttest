package uk.co.massimocarli.lifecyclecomponenttest


import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class LifecycleEspressoTest {

    @get:Rule
    val activityRule = ActivityTestRule(LifecycleActivity::class.java)

    @Test
    fun testRotation() {
        Thread.sleep(500)
        rotateScreen()
        Thread.sleep(500)
        rotateScreen()
        Thread.sleep(500)
        rotateScreen()
        Thread.sleep(500)
    }

    private fun rotateScreen() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val orientation = context.getResources().getConfiguration().orientation

        val activity = activityRule.activity
        activity.requestedOrientation = if (orientation == Configuration.ORIENTATION_PORTRAIT)
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        else
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}