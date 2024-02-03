package com.amarinag.randomuser.core.data.di

import com.amarinag.randomuser.core.data.repository.OnlineUserRepository
import com.amarinag.randomuser.core.data.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsUserRepository(onlineUserRepository: OnlineUserRepository): UserRepository
}