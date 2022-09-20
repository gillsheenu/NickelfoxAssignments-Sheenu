package com.example.nickelffoxassignments_sheenu.news.module

import com.example.nickelffoxassignments_sheenu.news.Constants
import com.example.nickelffoxassignments_sheenu.news.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesNewsService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }


}