package com.dlolhd.roomtest.fake

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dlolhd.roomtest.data.Product
import com.dlolhd.roomtest.data.ProductRepository

class FakeProductRepository: ProductRepository {
    val list = MutableLiveData<List<Product>>(FakeDataSource.productList)
    override val searchResults: MutableLiveData<List<Product>>
        get() = TODO("Not yet implemented")
    override val allProducts: LiveData<List<Product>>?
        get() = list

    override fun insertProduct(newProduct: Product) {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(name: String) {
        TODO("Not yet implemented")
    }

    override fun findProduct(name: String) {
        TODO("Not yet implemented")
    }
}