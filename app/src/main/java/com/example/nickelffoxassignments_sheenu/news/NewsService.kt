package com.example.nickelffoxassignments_sheenu.news

import com.example.nickelffoxassignments_sheenu.news.models.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/everything?apiKey=da48a10ea89b4580b1bc0beb63813d7b&q=india

//const val APIKEY="da48a10ea89b4580b1bc0beb63813d7b"
//const val APIKEY="a96b94650a004defafe34f089c2d967c"
const val APIKEY="0e9f7305c18a49d9b3329a3eb6ff7a5e"


interface NewsService {

   @GET("v2/everything?apiKey=$APIKEY")
    suspend fun getSearchNews(@Query("q") q:String,@Query("page") page:Int):Response<News>

    @GET("v2/top-headlines?apiKey=$APIKEY")
    suspend fun getTopHeadlines(@Query("country") country: String?=null,@Query("page") page:Int):Response<News>

    @GET("v2/top-headlines?apiKey=$APIKEY")
    suspend fun getCategoryWiseHeadline(@Query("category") category: String,@Query("page") page:Int):Response<News>


    @GET("v2/top-headlines?apiKey=$APIKEY")
    suspend fun getSourceWiseHeadline(@Query("sources")sources:String,@Query("page") page:Int):Response<News>


 //    @GET("v2/top-headlines/sources?apiKey=$APIKEY")
//    suspend fun getTopNews():Response<News>


//    @GET("v2/top-headlines/sources?apiKey=API_KEY")
//    suspend fun getSourceWiseNews(q: String):Response<News>
//
//    @GET("v2/top-headlines/sources?apiKey=API_KEY")
//    suspend fun getSourceWiseCountryHeadlines(@Query("country") country: String):Response<News>
//
//    @GET("v2/top-headlines/sources?apiKey=API_KEY")
//    suspend fun getSourceWiseCategoryHeadlines(@Query("category") category:String):Response<News>

}