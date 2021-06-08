package com.attafakkur.moviedbpro.ui.trending

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.attafakkur.moviedbpro.data.local.entity.TrendEntity
import com.attafakkur.moviedbpro.data.repository.DBRepository
import com.attafakkur.moviedbpro.vo.Resource
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrendViewModelTest : TestCase() {

    private lateinit var trendViewModel: TrendViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dbRepository: DBRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TrendEntity>>>
    @Mock
    private lateinit var pagedList: PagedList<TrendEntity>

    @Before
    public override fun setUp(){

        trendViewModel = TrendViewModel(dbRepository)
    }

    @Test
    fun testGetMoviesData(){
        val dummyTrend = Resource.success(pagedList)
        val trend = MutableLiveData<Resource<PagedList<TrendEntity>>>()
        trend.value = dummyTrend
        Mockito.`when`(dummyTrend.data?.size).thenReturn(1)
        Mockito.`when`(dbRepository.getTrending()).thenReturn(trend)
        val trendEntities = trendViewModel.getTrendData().value?.data
        Mockito.verify(dbRepository).getTrending()
        assertNotNull(trendEntities)
        assertEquals(1, trendEntities?.size)

        trendViewModel.getTrendData().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTrend)
    }
}