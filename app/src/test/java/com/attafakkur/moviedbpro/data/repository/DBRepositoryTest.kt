package com.attafakkur.moviedbpro.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.attafakkur.moviedbpro.data.datasource.RemoteDataSource
import com.attafakkur.moviedbpro.data.local.LocalDataSource
import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.attafakkur.moviedbpro.data.local.entity.SearchEntity
import com.attafakkur.moviedbpro.data.local.entity.TrendEntity
import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity
import com.attafakkur.moviedbpro.helper.LiveDataHelper.getValue
import com.attafakkur.moviedbpro.helper.PagedListUtils
import com.attafakkur.moviedbpro.utils.AppExecutors
import com.attafakkur.moviedbpro.utils.DataTesting
import com.attafakkur.moviedbpro.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@Suppress("UNCHECKED_CAST")
class DBRepositoryTest{

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val dbRepository = FakeDBRepositoryTest(remote, local, appExecutors)

    private val movieResponses = DataTesting.remoteMovieDummy()
    private val movieId = movieResponses[0].id

    private val tvResponses = DataTesting.remoteTvDummy()
    private val tvId = tvResponses[0].id

    private val trendResponses = DataTesting.remoteTrendDummy()
    private val trendId = trendResponses[0].id

    private val searchResponses = DataTesting.remoteSearchDummy()
    private val searchId = searchResponses[0].id

    private val detailMovie = DataTesting.remoteDetailMovieDummy(movieId)[0]
    private val detailTv = DataTesting.remoteDetailTvDummy(tvId)[0]
    private val detailTrend = DataTesting.remoteDetailTrendDummy(trendId)[0]
    private val detailSearch = DataTesting.remoteDetailSearchDummy(searchId)[0]

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        dbRepository.getFavoriteMovie()

        val movieEntities =
            Resource.success(PagedListUtils.mockPagedList(DataTesting.movieDummy()))
        verify(local).getFavoriteMovie()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size, movieEntities.data?.size)
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowsEntity>
        `when`(local.getFavoriteTvShow()).thenReturn(dataSourceFactory)
        dbRepository.getFavoriteTvShow()

        val tvShowEntity =
            Resource.success(PagedListUtils.mockPagedList(DataTesting.tvDummy()))
        verify(local).getFavoriteTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(tvResponses.size, tvShowEntity.data?.size)
    }

    @Test
    fun getDetailMovie() {
        val dataMovie = MutableLiveData<MoviesEntity>()
        dataMovie.value = detailMovie
        `when`(local.getMovieById(movieId)).thenReturn(dataMovie)

        val movieEntities = getValue(dbRepository.getDetailMovie(movieId))
        verify(local).getMovieById(movieId)
        if (movieEntities != null) {
            assertNotNull(movieEntities.data)
        }
        if (movieEntities != null) {
            assertEquals(movieResponses[0].id, movieEntities.data?.id)
        }

        assertNotNull(movieEntities)
        assertEquals(detailMovie.id, movieEntities?.data?.id)
        assertEquals(detailMovie.backdrop_path, movieEntities?.data?.backdrop_path)
        assertEquals(detailMovie.title, movieEntities?.data?.title)
        assertEquals(detailMovie.vote_average, movieEntities?.data?.vote_average)
        assertEquals(detailMovie.release_date, movieEntities?.data?.release_date)
        assertEquals(detailMovie.overview, movieEntities?.data?.overview)
    }

    @Test
    fun getDetailTv() {
        val dataTvShow = MutableLiveData<TvShowsEntity>()
        dataTvShow.value = detailTv
        `when`(local.getTvShowById(tvId)).thenReturn(dataTvShow)

        val tvEntities = getValue(dbRepository.getDetailTv(tvId))
        verify(local).getTvShowById(tvId)
        if (tvEntities != null) {
            assertNotNull(tvEntities.data)
        }
        if (tvEntities != null) {
            assertEquals(tvResponses[0].id, tvEntities.data?.id)
        }
        assertNotNull(tvEntities)
        assertEquals(detailTv.id, tvEntities?.data?.id)
        assertEquals(detailTv.backdrop_path, tvEntities?.data?.backdrop_path)
        assertEquals(detailTv.name, tvEntities?.data?.name)
        assertEquals(detailTv.vote_average, tvEntities?.data?.vote_average)
        assertEquals(detailTv.first_air_date, tvEntities?.data?.first_air_date)
        assertEquals(detailTv.overview, tvEntities?.data?.overview)
    }

    @Test
    fun getTrending() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TrendEntity>
        `when`(local.getFavoriteTrend()).thenReturn(dataSourceFactory)
        dbRepository.getFavoriteTrend()

        val trendEntities =
            Resource.success(PagedListUtils.mockPagedList(DataTesting.trendDummy()))
        verify(local).getFavoriteTrend()
        assertNotNull(trendEntities.data)
        assertEquals(trendResponses.size, trendEntities.data?.size)
    }

    @Test
    fun getDetailTrending() {
        val dataTrend = MutableLiveData<TrendEntity>()
        dataTrend.value = detailTrend
        `when`(local.getTrendById(tvId)).thenReturn(dataTrend)

        val trendEntities = getValue(dbRepository.getDetailTrending(tvId))
        verify(local).getTrendById(tvId)
        if (trendEntities != null) {
            assertNotNull(trendEntities.data)
        }
        if (trendEntities != null) {
            assertEquals(trendResponses[0].id, trendEntities.data?.id)
        }
        assertNotNull(trendEntities)
        assertEquals(detailTrend.id, trendEntities?.data?.id)
        assertEquals(detailTrend.backdrop_path, trendEntities?.data?.backdrop_path)
        assertEquals(detailTrend.title, trendEntities?.data?.title)
        assertEquals(detailTrend.vote_average, trendEntities?.data?.vote_average)
        assertEquals(detailTrend.release_date, trendEntities?.data?.release_date)
        assertEquals(detailTrend.overview, trendEntities?.data?.overview)
    }

    @Test
    fun getSearchMovie() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, SearchEntity>
        `when`(local.getFavoriteSearch()).thenReturn(dataSourceFactory)
        dbRepository.getFavoriteSearch()

        val searchEntities =
            Resource.success(PagedListUtils.mockPagedList(DataTesting.dummySearch()))
        verify(local).getFavoriteSearch()
        assertNotNull(searchEntities.data)
        assertEquals(searchResponses.size, searchEntities.data?.size)
    }

    @Test
    fun getDetailSearch() {
        val dataSearch = MutableLiveData<SearchEntity>()
        dataSearch.value = detailSearch
        `when`(local.getSearchById(searchId)).thenReturn(dataSearch)

        val searchEntities = getValue(dbRepository.getDetailSearch(searchId))
        verify(local).getSearchById(searchId)
        if (searchEntities != null) {
            assertNotNull(searchEntities.data)
        }
        if (searchEntities != null) {
            assertEquals(searchResponses[0].id, searchEntities.data?.id)
        }
        assertNotNull(searchEntities)
        assertEquals(detailSearch.id, searchEntities?.data?.id)
        assertEquals(detailSearch.backdrop_path, searchEntities?.data?.backdrop_path)
        assertEquals(detailSearch.title, searchEntities?.data?.title)
        assertEquals(detailSearch.vote_average, searchEntities?.data?.vote_average)
        assertEquals(detailSearch.release_date, searchEntities?.data?.release_date)
        assertEquals(detailSearch.overview, searchEntities?.data?.overview)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        dbRepository.getFavoriteMovie()

        val movieEntities =
            Resource.success(PagedListUtils.mockPagedList(DataTesting.movieDummy()))
        verify(local).getFavoriteMovie()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size, movieEntities.data?.size)
    }

    @Test
    fun setFavoriteMovie() {
        val movies = DataTesting.detailMovieDummy(movieId)[0]
        dbRepository.setFavoriteMovie(movies)
        verify(local).setMovieStatus(movies)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getFavoriteTvShow() {
        val dataSourceFactory =
                mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowsEntity>
        `when`(local.getFavoriteTvShow()).thenReturn(dataSourceFactory)
        dbRepository.getFavoriteTvShow()

        val tvEntities =
                Resource.success(PagedListUtils.mockPagedList(DataTesting.tvDummy()))
        verify(local).getFavoriteTvShow()
        assertNotNull(tvEntities.data)
        assertEquals(tvResponses.size, tvEntities.data?.size)
    }

    @Test
    fun setFavoriteTvShow() {
        val tv = DataTesting.detailTvDummy(tvId)[0]
        dbRepository.setFavoriteTvShow(tv)
        verify(local).setTvShowStatus(tv)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getFavoriteTrend() {
        val dataSourceFactory =
                mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TrendEntity>
        `when`(local.getFavoriteTrend()).thenReturn(dataSourceFactory)
        dbRepository.getFavoriteTrend()

        val trendEntities =
                Resource.success(PagedListUtils.mockPagedList(DataTesting.trendDummy()))
        verify(local).getFavoriteTrend()
        assertNotNull(trendEntities.data)
        assertEquals(trendResponses.size, trendEntities.data?.size)
    }

    @Test
    fun setFavoriteTrend() {
        val trend = DataTesting.detailTrendDummy(trendId)[0]
        dbRepository.setFavoriteTrend(trend)
        verify(local).setTrendStatus(trend)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getFavoriteSearch() {
        val dataSourceFactory =
                mock(DataSource.Factory::class.java) as DataSource.Factory<Int, SearchEntity>
        `when`(local.getFavoriteSearch()).thenReturn(dataSourceFactory)
        dbRepository.getFavoriteSearch()

        val searchEntities =
                Resource.success(PagedListUtils.mockPagedList(DataTesting.dummySearch()))
        verify(local).getFavoriteSearch()
        assertNotNull(searchEntities.data)
        assertEquals(searchResponses.size, searchEntities.data?.size)
    }

    @Test
    fun setFavoriteSearch() {
        val search = DataTesting.remoteDetailSearchDummy(searchId)[0]
        dbRepository.setFavoriteSearch(search)
        verify(local).setSearchStatus(search)
        verifyNoMoreInteractions(local)
    }

}