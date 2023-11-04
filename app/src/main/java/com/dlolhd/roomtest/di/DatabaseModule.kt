package com.dlolhd.roomtest.di

import android.content.Context
import androidx.room.Room
import com.dlolhd.roomtest.data.ProductDao
import com.dlolhd.roomtest.data.ProductRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): ProductRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            ProductRoomDatabase::class.java,
            "product_database"
        ).build()
    }

    @Provides
    fun provideProductDao(database: ProductRoomDatabase): ProductDao {
        return database.productDao()
    }

}