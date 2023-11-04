package com.dlolhd.roomtest.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

interface ProductRepository {
    val searchResults: MutableLiveData<List<Product>>
    val allProducts: LiveData<List<Product>>?
    fun insertProduct(newProduct: Product)
    fun deleteProduct(name: String)
    fun findProduct(name: String)
}


//class ProductLocalRepository(application: Context) : ProductRepository {
@Singleton
class ProductLocalRepositoryImpl @Inject constructor(
    private val productDao: ProductDao?
) : ProductRepository {

    override val searchResults = MutableLiveData<List<Product>>()
    //private var productDao: ProductDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    override val allProducts: LiveData<List<Product>>?

    init {
        /*val db: ProductRoomDatabase? =
            ProductRoomDatabase.getDatabase(application)*/
        //productDao = db?.productDao()
        allProducts = productDao?.getAllProducts()
    }

    override fun insertProduct(newProduct: Product) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncInsert(newProduct)
        }
    }

    private fun asyncInsert(product: Product) {
        productDao?.insertProduct(product)
    }

    override fun deleteProduct(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncDelete(name)
        }
    }

    private fun asyncDelete(name: String) {
        productDao?.deleteProduct(name)
    }

    override fun findProduct(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(name).await()
        }
    }

    private fun asyncFind(name: String): Deferred<List<Product>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async productDao?.findProduct(name)
        }

}



















