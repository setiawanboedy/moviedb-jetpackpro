package com.attafakkur.moviedbpro.helper

import android.os.SystemClock
import android.view.View
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import com.google.android.material.tabs.TabLayout
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf

object TestHelper {
    fun selectTab(index: Int) : ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return AllOf.allOf(
                    ViewMatchers.isDisplayed(),
                    ViewMatchers.isAssignableFrom(TabLayout::class.java)
                )
            }

            override fun getDescription(): String = "Tab index $index"

            override fun perform(uiController: UiController?, view: View?) {
                val tabLayout = view as TabLayout
                val tabIndex = tabLayout.getTabAt(index)
                tabIndex ?: throw PerformException.Builder()
                    .withCause(Throwable("No tab index $index"))
                    .build()
                tabIndex.select()
            }

        }
    }
    fun delayTwoSecond(){
        SystemClock.sleep(2000)
    }
}