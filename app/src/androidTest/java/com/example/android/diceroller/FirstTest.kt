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
import org.hamcrest.core.StringContains.containsString
import org.hamcrest.core.StringStartsWith.startsWith
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
//@LargeTest
class ClickResetTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

//    @Test
    fun checkHelloText() {
        onView(withText("HELLO")).check(matches(isDisplayed()))
    }

//    @Test
    fun checkRollAndDiceButtons() {
        onView(withId(R.id.roll_button)).perform(click())

//        onView(withText(("Your digit")).check(matches(isDisplayed()))
        onView(withId(R.id.text_label)).check(matches(withText(startsWith("Your digit is"))))

//        for (i in 1..100) {
//            onView(withId(R.id.roll_button)).perform(click())
//            onView(withId(R.id.text_label)).check(matches(withText(startsWith("Your digit is"))))
//        }
        onView(withId(R.id.reset_button)).perform(click())

        onView(withText("HELLO")).check(matches(isDisplayed()))
    }

//    @Test
    fun checkSpinner() {
//        onView(withId(R.id.test_spinner)).perform(click())



        var randomInt = Random().nextInt(6)
//        var randomInt = 1
        var myString: String = ""

//        for (i in 0..100) {
            myString = when (randomInt) {
                0 -> "Java"
                1 -> "PHP"
                2 -> "Kotlin"
                3 -> "Javascript"
                4 -> "Python"
                else -> "Swift"
            }
            onView(withId(R.id.test_spinner)).perform(click())
            onData(anything()).atPosition(randomInt).perform(click())
            onView(withId(R.id.test_spinner)).check(matches(withSpinnerText(containsString(myString))))
//            randomInt = Random().nextInt(6)
//            myString = when (randomInt) {
//                0 -> "Java"
//                1 -> "PHP"
//                2 -> "Kotlin"
//                3 -> "Javascript"
//                4 -> "Python"
//                else -> "Swift"
//            }
//            Thread.sleep(1000)
//        }
//        onData(anything()).atPosition(randomInt).perform(click())
//        onView(withId(R.id.test_spinner)).check(matches(withSpinnerText(containsString(myString))))

//        onData(allOf(`is`(instanceOf(String::class.java)),
//            `is`("Java"))).perform(click())
//        onView(withId(R.id.test_spinner))
//            .check(matches(withText(containsString("Java"))))
    }
    @Test
    fun bacchanalian() {

        var randomInt = Random().nextInt(2)

        for (i in 1..1000) {
            randomInt = Random().nextInt(2)
            when (randomInt) {
                0 -> checkRollAndDiceButtons()
                1 -> checkSpinner()
                else -> {
                    checkHelloText()
                }
            }
            Thread.sleep(500)
        }
    }
}