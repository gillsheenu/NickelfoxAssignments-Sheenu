package com.example.nickelffoxassignments_sheenu.news

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.nickelffoxassignments_sheenu.news.db.NewsDatabase
import com.example.nickelffoxassignments_sheenu.news.paging.NewsPagingSource
import com.example.nickelffoxassignments_sheenu.news.paging.NewsRemoteMediator
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class NewsRepository @Inject constructor(var newsService: NewsService, var newsDatabase: NewsDatabase) {



    fun getSearchNews(query:String)= Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        remoteMediator = NewsRemoteMediator(newsService,newsDatabase,"everything",query),
        pagingSourceFactory = { NewsPagingSource(newsService,"everything",query) }
    ).liveData

    fun getTopNews( query:String)= Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        remoteMediator = NewsRemoteMediator(newsService,newsDatabase,"TopHeadlines",query),
        pagingSourceFactory = { NewsPagingSource(newsService,"TopHeadlines",query) }
    ).liveData

    fun getTopSourceNews( query:String)= Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        remoteMediator = NewsRemoteMediator(newsService,newsDatabase,"TopSourceHeadlines",query),
        pagingSourceFactory = { NewsPagingSource(newsService,"TopSourceHeadlines",query) }
    ).liveData

    fun getTopCategoryNews( query:String)= Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        remoteMediator = NewsRemoteMediator(newsService,newsDatabase,"TopCategoryHeadlines",query),
        pagingSourceFactory = { NewsPagingSource(newsService,"TopCategoryHeadlines",query) }
    ).liveData


//    fun getSearchNews( query:String)= Pager(
//        config = PagingConfig(pageSize = 20, maxSize = 100),
//        pagingSourceFactory = {NewsPagingSource(newsService,"everything",query)}
//    ).liveData
//
//    fun getTopNews( query:String)= Pager(
//        config = PagingConfig(pageSize = 20, maxSize = 100),
//        pagingSourceFactory = {NewsPagingSource(newsService,"TopHeadlines",query)}
//    ).liveData
//
//    fun getTopSourceNews( query:String)= Pager(
//        config = PagingConfig(pageSize = 20, maxSize = 100),
//        pagingSourceFactory = {NewsPagingSource(newsService,"TopSourceHeadlines",query)}
//    ).liveData
//
//    fun getTopCategoryNews( query:String)= Pager(
//        config = PagingConfig(pageSize = 20, maxSize = 100),
//        pagingSourceFactory = {NewsPagingSource(newsService,"TopCategoryHeadlines",query)}
//    ).liveData


//    val newsLiveData= MutableLiveData<News>()
//
//     suspend fun getSearchItem(q:String){
//         val result=newsService.getSearchNews(q)
//         if(result.isSuccessful){
//             newsLiveData.postValue(result.body())
//         }
//
//     }
//
//    suspend fun getTopHeadlines(country:String?){
//        val result=newsService.getTopHeadlines(country)
//        if(result.isSuccessful){
//            newsLiveData.postValue(result.body())
//        }
//    }
////    suspend fun getTopNews(){
////        val result=newsService.getTopHeadlines()
////        if(result.isSuccessful){
////            newsLiveData.postValue(result.body())
////        }
////    }
//
//
//    suspend fun getCategoryWiseNews(c:String){
//        val result=newsService.getCategoryWiseHeadline(c)
//        if(result.isSuccessful){
//            newsLiveData.postValue(result.body())
//        }
//    }
//
//    suspend fun getSourceHeadlineNews(q:String){
//        val result=newsService.getSourceWiseHeadline(q)
//        if(result.isSuccessful){
//            newsLiveData.postValue(result.body())
//        }
//    }
//
//    suspend fun getSourceNews(q:String){
//        val result=newsService.getSourceWiseNews(q)
//        if(result.isSuccessful){
//            newsLiveData.postValue(result.body())
//        }
//    }
//
//    suspend fun getSourceCategoryNews(q:String){
//        val result=newsService.getSourceWiseCategoryHeadlines(q)
//        if(result.isSuccessful){
//            newsLiveData.postValue(result.body())
//        }
//    }
//
//    suspend fun getSourceCountryNews(q:String){
//        val result=newsService.getSourceWiseCountryHeadlines(q)
//        if(result.isSuccessful){
//            newsLiveData.postValue(result.body())
//        }
//    }
}