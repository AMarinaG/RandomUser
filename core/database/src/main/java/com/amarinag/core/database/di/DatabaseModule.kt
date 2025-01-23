package com.amarinag.core.database.di

import android.content.Context
import androidx.room.Room
import com.amarinag.core.database.RandomUserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesRandomUserDatabase(
        @ApplicationContext context: Context
    ): RandomUserDatabase = Room.databaseBuilder(
        context = context,
        klass = RandomUserDatabase::class.java,
        name = "randomuser-db"
    ).build()
}