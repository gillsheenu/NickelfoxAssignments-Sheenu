package com.example.nickelffoxassignments_sheenu.news.module

import android.content.Context
import androidx.room.Room
import com.example.nickelffoxassignments_sheenu.news.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NewsDatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(context, NewsDatabase::class.java,"newsDB").build()
    }


}