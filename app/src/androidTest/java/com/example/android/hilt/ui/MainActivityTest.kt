package com.example.android.hilt.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.hilt.CustomTextApp
import com.example.android.hilt.R
import com.example.android.hilt.data.InMemoryLogger
import com.example.android.hilt.data.LoggerDataSource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.TestCase
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainActivityTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun happyPath() {

        ActivityScenario.launch(MainActivity::class.java)
        // Check Buttons fragment screen is displayed
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Tap on Button 1
        Espresso.onView(ViewMatchers.withId(R.id.button1)).perform(ViewActions.click())

        // Navigate to Logs screen
        Espresso.onView(ViewMatchers.withId(R.id.all_logs)).perform(ViewActions.click())

        // Check Logs fragment screen is displayed
        Espresso.onView(ViewMatchers.withText(Matchers.containsString("Interaction with 'Button 1'")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}