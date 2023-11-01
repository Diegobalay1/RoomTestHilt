package com.dlolhd.roomtest

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class ProductRepository(application: Application) {

    val searchResults = MutableLiveData<List<Product>>()
    private var productDao: ProductDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allProducts: LiveData<List<Product>>?

    init {
        val db: ProductRoomDatabase? =
            ProductRoomDatabase.getDatabase(application)
        productDao = db?.productDao()
        allProducts = productDao?.getAllProducts()
    }

    fun insertProduct(newProduct: Product) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncInsert(newProduct)
        }
    }

    private fun asyncInsert(product: Product) {
        productDao?.insertProduct(product)
    }

    fun deleteProduct(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncDelete(name)
        }
    }

    private fun asyncDelete(name: String) {
        productDao?.deleteProduct(name)
    }

    fun findProduct(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(name).await()
        }
    }

    private fun asyncFind(name: String): Deferred<List<Product>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async productDao?.findProduct(name)
        }

}



















