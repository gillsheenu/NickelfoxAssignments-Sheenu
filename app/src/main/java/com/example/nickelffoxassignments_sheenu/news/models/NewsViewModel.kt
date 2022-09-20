package com.example.nickelffoxassignments_sheenu.news.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class NewsViewModel @Inject constructor(var repository: NewsRepository):ViewModel() {


    fun searchNews(query:String)=repository.getSearchNews(query).cachedIn(viewModelScope)

    fun topNews(query:String)=repository.getTopNews(query).cachedIn(viewModelScope)

    fun topSourceNews(query:String)=repository.getTopSourceNews(query).cachedIn(viewModelScope)

    fun topCategoryNews(query:String)=repository.getTopCategoryNews(query).cachedIn(viewModelScope)

//    suspend fun insertBookmark(bookmark:Bookmark){
//        repository.insertBookmarks(bookmark)
//    }
//    fun getBookmark(){
//        repository.getBookmark()
//    }



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
