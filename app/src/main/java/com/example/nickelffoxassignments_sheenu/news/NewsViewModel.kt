package com.example.nickelffoxassignments_sheenu.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.http.Query
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class NewsViewModel @Inject constructor(var repository: NewsRepository):ViewModel() {


    fun SearchNews(query:String)=repository.getSearchNews(query).cachedIn(viewModelScope)

    fun TopNews(query:String)=repository.getTopNews(query).cachedIn(viewModelScope)

    fun TopSourceNews(query:String)=repository.getTopSourceNews(query).cachedIn(viewModelScope)

    fun TopCategoryNews(query:String)=repository.getTopCategoryNews(query).cachedIn(viewModelScope)



//    val list=repository.getNews().cachedIn(viewModelScope)

//    val newsLiveData = repository.newsLiveData
//
//
//    suspend fun search(q: String) {
//        repository.getSearchItem(q)
//    }
//
//    suspend fun topHeadlines(country: String?) {
//        repository.getTopHeadlines(country)
//    }
//
//    suspend fun topSourceHeadlines(source: String) {
//        repository.getSourceHeadlineNews(source)
//    }
//
//    suspend fun topCategoryHeadlines(category: String) {
//        repository.getCategoryWiseNews(category)
//    }

}
