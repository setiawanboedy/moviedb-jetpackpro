package com.attafakkur.moviedbpro.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.attafakkur.moviedbpro.data.local.entity.SearchEntity
import com.attafakkur.moviedbpro.data.local.entity.TrendEntity
import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity
import com.attafakkur.moviedbpro.data.repository.DBRepository
import com.attafakkur.moviedbpro.helper.PagedTestMovie
import com.attafakkur.moviedbpro.helper.PagedTestSearch
import com.attafakkur.moviedbpro.helper.PagedTestTrend
import com.attafakkur.moviedbpro.helper.PagedTestTv
import com.attafakkur.moviedbpro.utils.DataTesting
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest : TestCase() {
    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dbRepository: DBRepository

    @Mock
    private lateinit var observerMovie: Observer<PagedList<MoviesEntity>>
    @Mock
    private lateinit var observerTv: Observer<PagedList<TvShowsEntity>>
    @Mock
    private lateinit var observerTrend: Observer<PagedList<TrendEntity>>
    @Mock
    private lateinit var observerSearch: Observer<PagedList<SearchEntity>>

    @Before
    public override fun setUp() {
        viewModel = FavoriteViewModel(dbRepository)
    }

    @Test
    fun getFavoriteMovie() {
        val expected = MutableLiveData<PagedList<MoviesEntity>>()
        expected.value = PagedTestMovie.snapshot(DataTesting.movieDummy())

        `when`(dbRepository.getFavoriteMovie()).thenReturn(expected)

        viewModel.getFavoriteMovie().observeForever(observerMovie)
        verify(observerMovie).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavoriteMovie().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun getFavoriteTv() {
        val expected = MutableLiveData<PagedList<TvShowsEntity>>()
        expected.value = PagedTestTv.snapshot(DataTesting.tvDummy())

        `when`(dbRepository.getFavoriteTvShow()).thenReturn(expected)

        viewModel.getFavoriteTv().observeForever(observerTv)
        verify(observerTv).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavoriteTv().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun getFavoriteTrend() {
        val expected = MutableLiveData<PagedList<TrendEntity>>()
        expected.value = PagedTestTrend.snapshot(DataTesting.trendDummy())

        `when`(dbRepository.getFavoriteTrend()).thenReturn(expected)

        viewModel.getFavoriteTrend().observeForever(observerTrend)
        verify(observerTrend).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavoriteTrend().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun getFavoriteSearch() {
        val expected = MutableLiveData<PagedList<SearchEntity>>()
        expected.value = PagedTestSearch.snapshot(DataTesting.dummySearch())

        `when`(dbRepository.getFavoriteSearch()).thenReturn(expected)

        viewModel.getFavoriteSearch().observeForever(observerSearch)
        verify(observerSearch).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavoriteSearch().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

}