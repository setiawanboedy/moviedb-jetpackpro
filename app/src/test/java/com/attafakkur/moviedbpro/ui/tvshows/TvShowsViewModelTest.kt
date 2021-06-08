package com.attafakkur.moviedbpro.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity
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
class TvShowsViewModelTest : TestCase() {

    private lateinit var tvViewModel: TvShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dbRepository: DBRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowsEntity>>>
    @Mock
    private lateinit var pagedList: PagedList<TvShowsEntity>

    @Before
    public override fun setUp(){
        tvViewModel = TvShowsViewModel(dbRepository)
    }

    @Test
    fun testGetMoviesData(){

        val dummyTv = Resource.success(pagedList)
        val tv = MutableLiveData<Resource<PagedList<TvShowsEntity>>>()
        tv.value = dummyTv
        Mockito.`when`(dummyTv.data?.size).thenReturn(1)
        Mockito.`when`(dbRepository.getTvShows()).thenReturn(tv)
        val tvEntities = tvViewModel.getTvData().value?.data
        Mockito.verify(dbRepository).getTvShows()
        assertNotNull(tvEntities)
        assertEquals(1, tvEntities?.size)

        tvViewModel.getTvData().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTv)
    }
}