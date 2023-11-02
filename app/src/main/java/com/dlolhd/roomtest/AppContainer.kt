package com.dlolhd.roomtest

import android.content.Context
import androidx.room.Room
import com.dlolhd.roomtest.data.LocalDataSource
import com.dlolhd.roomtest.data.ProductLocalRepository
import com.dlolhd.roomtest.data.ProductRepository
import com.dlolhd.roomtest.data.ProductRoomDatabase
import com.dlolhd.roomtest.data.RemoteDataSource

interface AppContainer {
    val repository: ProductRepository
}

class DefaultAppContainer(application: Context): AppContainer {

    private val database = Room.databaseBuilder(
        application,
        ProductRoomDatabase::class.java,
        "product_database"
    ).build()

    private val dao = database.productDao()

    private val localDataSource = LocalDataSource()
    private val remoteDataSource = RemoteDataSource()

    // repository is not private; it'll be exposed
    override val repository: ProductRepository by lazy {
        //ProductLocalRepository(application)
        ProductLocalRepository(dao)
    }

}