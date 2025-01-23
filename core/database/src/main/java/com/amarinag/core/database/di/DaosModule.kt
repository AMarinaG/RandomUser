package com.amarinag.core.database.di

import com.amarinag.core.database.RandomUserDatabase
import com.amarinag.core.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun provideUserDao(database: RandomUserDatabase): UserDao = database.userDao()
}