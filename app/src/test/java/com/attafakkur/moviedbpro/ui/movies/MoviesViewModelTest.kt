package com.attafakkur.moviedbpro.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.attafakkur.moviedbpro.data.repository.DBRepository
import com.attafakkur.moviedbpro.vo.Resource
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
class MoviesViewModelTest : TestCase() {
    private lateinit var moviesViewModel: MoviesViewModel
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dbRepository: DBRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MoviesEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Before
    public override fun setUp(){
        moviesViewModel = MoviesViewModel(dbRepository)
    }

    @Test
    fun testGetMoviesData(){
        val dummyMovie = Resource.success(pagedList)
        val movie = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        movie.value = dummyMovie
        `when`(dummyMovie.data?.size).thenReturn(1)
        `when`(dbRepository.getMovies()).thenReturn(movie)
        val movieEntities = moviesViewModel.getMoviesData().value?.data
        verify(dbRepository).getMovies()
        assertNotNull(movieEntities)
        assertEquals(1, movieEntities?.size)

        moviesViewModel.getMoviesData().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

}