package com.dlolhd.roomtest.di

import com.dlolhd.roomtest.data.ProductLocalRepositoryImpl
import com.dlolhd.roomtest.data.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
annotation class LocalData

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindLocalRepository(impl: ProductLocalRepositoryImpl): ProductRepository

}