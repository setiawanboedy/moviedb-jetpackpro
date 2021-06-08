package com.attafakkur.moviedbpro.ui.home

import android.content.Context
import android.view.KeyEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.attafakkur.moviedbpro.R
import com.attafakkur.moviedbpro.helper.MovieApiHelper
import com.attafakkur.moviedbpro.helper.TestHelper.delayTwoSecond
import com.attafakkur.moviedbpro.helper.TestHelper.selectTab
import com.attafakkur.moviedbpro.helper.TvApiHelper
import com.attafakkur.moviedbpro.utils.EspressoIdlingResource.idlingResource
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.properties.Delegates

class HomeMoviesActivityTest : TestCase() {
    private val getFakeMovieApi = MovieApiHelper.getApi[5]
    private val getFakeTvApi = TvApiHelper.getApi[5]

    private lateinit var instContext: Context
    private var index by Delegates.notNull<Int>()

    @Before
    public override fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(idlingResource)
        instContext = InstrumentationRegistry.getInstrumentation().context
        index = 5
    }

    @After
    override fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun testPopulateTrend() {
        onView(withId(R.id.rv_trend)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_trend))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(5))
    }

    @Test
    fun testPopulateMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(swipeDown())
    }

    @Test
    fun testDetailMoviesWithData() {
        delayTwoSecond()
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(5))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    index,
                    click()
                )
            )

        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(
            matches(withText(getFakeMovieApi.title))
        )

        onView(withId(R.id.language_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.language_detail)).check(
            matches(withText("Language: ${getFakeMovieApi.original_language}"))
        )

        onView(withId(R.id.date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.date_detail)).check(
            matches(withText("Release Date: ${getFakeMovieApi.release_date}"))
        )

        onView(withId(R.id.detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_rating)).check(
            matches(withText("Rating: ${getFakeMovieApi.vote_average}"))
        )

        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(
            matches(withText(getFakeMovieApi.overview))
        )

        onView(withId(R.id.backDrop)).check(matches(isDisplayed()))

        onView(withId(R.id.back_detail)).perform(click())
    }

    @Test
    fun testTabToTvRecyclerView() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.tabs))
            .perform(selectTab(1))
        delayTwoSecond()
        onView(withId(R.id.rv_tvShows)).perform(swipeDown())
        onView(withId(R.id.rv_tvShows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShows))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(index))
    }

    @Test
    fun testDetailTvShowWithData() {
        onView(withId(R.id.tabs))
            .perform(selectTab(1))

        delayTwoSecond()

        onView(withId(R.id.rv_tvShows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShows))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(5))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    index,
                    click()
                )
            )
        delayTwoSecond()

        onView(withId(R.id.detail_title_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title_tv)).check(
            matches(withText(getFakeTvApi.name))
        )

        onView(withId(R.id.language_detail_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.language_detail_tv)).check(
            matches(withText("Language: ${getFakeTvApi.original_language}"))
        )

        onView(withId(R.id.date_detail_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.date_detail_tv)).check(
            matches(withText("Release Date: ${getFakeTvApi.first_air_date}"))
        )

        onView(withId(R.id.detail_rating_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_rating_tv)).check(
            matches(withText("Rating: ${getFakeTvApi.vote_average}"))
        )

        onView(withId(R.id.overview_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_tv)).check(
            matches(withText(getFakeTvApi.overview)))

        onView(withId(R.id.backDrop_tv)).check(matches(isDisplayed()))

        onView(withId(R.id.back_detail_tv)).perform(click())
    }

    @Test
    fun testSearch() {
        onView(withId(R.id.search_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.search_movie)).perform(click())
        onView(withId(R.id.search_src_text)).perform(typeText("marvel"))
        onView(withId(R.id.search_movie)).perform(pressKey(KeyEvent.KEYCODE_ENTER))
    }

    @Test
    fun testAddToFavorite() {
        onView(withId(R.id.rv_movies))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(5))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    index,
                    click()
                )
            )
        onView(withId(R.id.star_border)).perform(click())

        onView(isRoot()).perform(pressBack())

        onView(withId(R.id.rv_trend))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(2))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    index,
                    click()
                )
            )
        onView(withId(R.id.star_border)).perform(click())
    }
    @Test
    fun testFavoriteList() {
        onView(withId(R.id.favorite)).perform(click())
    }
}