package com.example.android.diceroller

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.*
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.StringContains.containsString
import org.hamcrest.core.StringStartsWith.startsWith
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class HelloDiceEspressoTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun listGoesOverTheFold() {
        onView(withText("HELLO")).check(matches(isDisplayed()))
    }
}

@RunWith(AndroidJUnit4::class)
//@LargeTest
class ClickResetTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkHelloText() {
        onView(withText("HELLO")).check(matches(isDisplayed()))
    }

    @Test
    fun checkRollAndDiceButtons() {
        onView(withId(R.id.roll_button)).perform(click())

//        onView(withText(("Your digit")).check(matches(isDisplayed()))
        onView(withId(R.id.text_label)).check(matches(withText(startsWith("Your digit is"))))

        for (i in 1..10) {
            onView(withId(R.id.roll_button)).perform(click())
            onView(withId(R.id.text_label)).check(matches(withText(startsWith("Your digit is"))))
        }
        onView(withId(R.id.reset_button)).perform(click())

        onView(withText("HELLO")).check(matches(isDisplayed()))
    }

    @Test
    fun checkSpinner() {
        onView(withId(R.id.test_spinner)).perform(click())
//
        onData(anything()).atPosition(1).perform(click())
        onView(withId(R.id.test_spinner)).check(matches(withSpinnerText(containsString("PHP"))))

//        onData(allOf(`is`(instanceOf(String::class.java)),
//            `is`("Java"))).perform(click())
//        onView(withId(R.id.test_spinner))
//            .check(matches(withText(containsString("Java"))))
    }

}