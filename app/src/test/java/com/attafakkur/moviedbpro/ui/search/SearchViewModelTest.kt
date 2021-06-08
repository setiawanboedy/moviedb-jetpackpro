package com.attafakkur.moviedbpro.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.attafakkur.moviedbpro.data.local.entity.SearchEntity
import com.attafakkur.moviedbpro.data.repository.DBRepository
import com.attafakkur.moviedbpro.vo.Resource
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest : TestCase() {

    private lateinit var searchViewModel: SearchViewModel

    private val keyWord = "marvel"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dbRepository: DBRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<SearchEntity>>>
    @Mock
    private lateinit var pagedList: PagedList<SearchEntity>

    @Before
    public override fun setUp() {
        searchViewModel = SearchViewModel(dbRepository)
    }

    @Test
    fun getSearchData() {
        val dummySearch = Resource.success(pagedList)
        val search = MutableLiveData<Resource<PagedList<SearchEntity>>>()
        search.value = dummySearch
        `when`(dummySearch.data?.size).thenReturn(1)
        `when`(dbRepository.getSearch(keyWord)).thenReturn(search)
        val movieEntities = searchViewModel.getSearchData(keyWord).value?.data
        Mockito.verify(dbRepository).getSearch(keyWord)
        assertNotNull(movieEntities)
        assertEquals(1, movieEntities?.size)

        searchViewModel.getSearchData(keyWord).observeForever(observer)
        Mockito.verify(observer).onChanged(dummySearch)
    }
}