package com.example.nickelffoxassignments_sheenu.news.di

import com.example.nickelffoxassignments_sheenu.news.utils.Constants
import com.example.nickelffoxassignments_sheenu.news.NewsService
import com.example.nickelffoxassignments_sheenu.uploadimage.data.network.UploadImage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Named("news")
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
    fun providesNewsService( @Named("news") retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }



    @Named("image")
    @Singleton
    @Provides
    fun providesImageRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.IMAGE_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

    }

    @Provides
    fun providesIntercepter():HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }


    @Provides
    fun providesOkHttpClient(authInterceptor: HttpLoggingInterceptor):OkHttpClient{

        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()

    }

    @Singleton
    @Provides
    fun providesUploadImage(@Named("image")retrofit: Retrofit): UploadImage {
        return retrofit.create(UploadImage::class.java)
    }





}