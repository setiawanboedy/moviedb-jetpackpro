package com.attafakkur.moviedbpro.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.attafakkur.moviedbpro.data.local.entity.SearchEntity
import com.attafakkur.moviedbpro.data.local.entity.TrendEntity
import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity
import com.attafakkur.moviedbpro.data.repository.DBRepository
import com.attafakkur.moviedbpro.utils.DataTesting
import com.attafakkur.moviedbpro.vo.Resource
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest : TestCase() {

    private lateinit var detailViewModel: DetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dbRepository: DBRepository
    @Mock
    private lateinit var observerMovie: Observer<Resource<MoviesEntity>>
    @Mock
    private lateinit var observerTv: Observer<Resource<TvShowsEntity>>
    @Mock
    private lateinit var observerTrend: Observer<Resource<TrendEntity>>
    @Mock
    private lateinit var observerSearch: Observer<Resource<SearchEntity>>
    @Mock
    private lateinit var observerRelateMovie: Observer<Resource<PagedList<MoviesEntity>>>
    @Mock
    private lateinit var pagedListMovie: PagedList<MoviesEntity>
    @Mock
    private lateinit var observerRelateTv: Observer<Resource<PagedList<TvShowsEntity>>>
    @Mock
    private lateinit var pagedListTv: PagedList<TvShowsEntity>

    @Before
    public override fun setUp() {
        detailViewModel = DetailViewModel(dbRepository)
    }

    @Test
    fun testGetDetailMovies() {
        val dummyMovie = Resource.success(DataTesting.movieDummy()[0])
        val movieId = dummyMovie.data?.id

        val movie = MutableLiveData<Resource<MoviesEntity>>()
        movie.value = dummyMovie
        `when`(movieId?.let { dbRepository.getDetailMovie(it) }).thenReturn(movie)

        if (movieId != null) {
            detailViewModel.getDetailMovies(movieId).observeForever(observerMovie)
        }
        verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun testGetDetailTv() {
        val dummyTv = Resource.success(DataTesting.tvDummy()[0])
        val movieId = dummyTv.data?.id

        val tv = MutableLiveData<Resource<TvShowsEntity>>()
        tv.value = dummyTv
        `when`(movieId?.let { dbRepository.getDetailTv(it) }).thenReturn(tv)

        if (movieId != null) {
            detailViewModel.getDetailTv(movieId).observeForever(observerTv)
        }
        verify(observerTv).onChanged(dummyTv)
    }

    @Test
    fun testGetDetailTrend() {
        val dummyTrend = Resource.success(DataTesting.trendDummy()[0])
        val trendId = dummyTrend.data?.id

        val trend = MutableLiveData<Resource<TrendEntity>>()
        trend.value = dummyTrend
        `when`(trendId?.let { dbRepository.getDetailTrending(it) }).thenReturn(trend)

        if (trendId != null) {
            detailViewModel.getDetailTrend(trendId).observeForever(observerTrend)
        }
        verify(observerTrend).onChanged(dummyTrend)
    }

    @Test
    fun testGetRelateDataMovie() {
        val dummyPagedList = Resource.success(pagedListMovie)
        val dummyTrend = Resource.success(DataTesting.movieDummy()[0])
        val relateId = dummyTrend.data?.id

        `when`(dummyPagedList.data?.size).thenReturn(1)

        val relate = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        relate.value = dummyPagedList
        `when`(relateId?.let { dbRepository.getRelateMovie(it) }).thenReturn(relate)
        val movieEntities = relateId?.let { detailViewModel.getRelateDataMovie(it).value?.data }
        if (relateId != null) {
            verify(dbRepository).getRelateMovie(relateId)
        }
        assertNotNull(movieEntities)
        assertEquals(1, movieEntities?.size)

        if (relateId != null) {
            detailViewModel.getRelateDataMovie(relateId).observeForever(observerRelateMovie)
        }
        verify(observerRelateMovie).onChanged(dummyPagedList)
    }

    @Test
    fun testGetRelateDataTv() {
        val dummyPagedList = Resource.success(pagedListTv)
        val dummyTv = Resource.success(DataTesting.tvDummy()[0])
        val relateId = dummyTv.data?.id

        `when`(dummyPagedList.data?.size).thenReturn(1)

        val relate = MutableLiveData<Resource<PagedList<TvShowsEntity>>>()
        relate.value = dummyPagedList
        `when`(relateId?.let { dbRepository.getRelateTv(it) }).thenReturn(relate)
        val movieEntities = relateId?.let { detailViewModel.getRelateDataTv(it).value?.data }
        if (relateId != null) {
            verify(dbRepository).getRelateTv(relateId)
        }
        assertNotNull(movieEntities)
        assertEquals(1, movieEntities?.size)

        if (relateId != null) {
            detailViewModel.getRelateDataTv(relateId).observeForever(observerRelateTv)
        }
        verify(observerRelateTv).onChanged(dummyPagedList)
    }
    @Test
    fun testGetDetailSearch() {
        val dummySearch = Resource.success(DataTesting.dummySearch()[0])
        val searchId = dummySearch.data?.id

        val search = MutableLiveData<Resource<SearchEntity>>()
        search.value = dummySearch
        `when`(searchId?.let { dbRepository.getDetailSearch(it) }).thenReturn(search)

        if (searchId != null) {
            detailViewModel.getDetailSearch(searchId).observeForever(observerSearch)
        }
        verify(observerSearch).onChanged(dummySearch)
    }
    @Test
    fun testSetFavoriteMovie() {
        val dummyFv = Resource.success(DataTesting.movieDummy()[0])
        val id = dummyFv.data?.id
        val detailMovie = Resource.success(id?.let { DataTesting.detailMovieDummy(it)[0] })
        val movie = MutableLiveData<Resource<MoviesEntity>>()
        movie.value = detailMovie

        detailMovie.data?.let { detailViewModel.setFavoriteMovie(it) }

        verify(dbRepository).setFavoriteMovie(movie.value!!.data as MoviesEntity)
        verifyNoMoreInteractions(observerMovie)
    }
    @Test
    fun testSetFavoriteTrend() {
        val dummyFv = Resource.success(DataTesting.trendDummy()[0])
        val id = dummyFv.data?.id
        val detailMovie = Resource.success(id?.let { DataTesting.detailTrendDummy(it)[0] })
        val movie = MutableLiveData<Resource<TrendEntity>>()
        movie.value = detailMovie

        detailMovie.data?.let { detailViewModel.setFavoriteTrend(it) }

        verify(dbRepository).setFavoriteTrend(movie.value!!.data as TrendEntity)
        verifyNoMoreInteractions(observerMovie)
    }
    @Test
    fun testSetFavoriteTv() {
        val dummyFv = Resource.success(DataTesting.tvDummy()[0])
        val id = dummyFv.data?.id
        val detaiTv = Resource.success(id?.let { DataTesting.detailTvDummy(it)[0] })
        val tv = MutableLiveData<Resource<TvShowsEntity>>()
        tv.value = detaiTv

        detaiTv.data?.let { detailViewModel.setFavoriteTv(it) }

        verify(dbRepository).setFavoriteTvShow(tv.value!!.data as TvShowsEntity)
        verifyNoMoreInteractions(observerMovie)
    }
    @Test
    fun testSetFavoriteSearch() {
        val dummyFv = Resource.success(DataTesting.dummySearch()[0])
        val id = dummyFv.data?.id
        val detailSearch = Resource.success(id?.let { DataTesting.remoteDetailSearchDummy(it)[0] })
        val movie = MutableLiveData<Resource<SearchEntity>>()
        movie.value = detailSearch

        detailSearch.data?.let { detailViewModel.setFavoriteSearch(it) }

        verify(dbRepository).setFavoriteSearch(movie.value!!.data as SearchEntity)
        verifyNoMoreInteractions(observerMovie)
    }

}