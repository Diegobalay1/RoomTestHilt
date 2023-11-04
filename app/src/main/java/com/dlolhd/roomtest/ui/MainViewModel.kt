package com.dlolhd.roomtest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dlolhd.roomtest.data.Product
import com.dlolhd.roomtest.data.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//class MainViewModel(application: Application) : AndroidViewModel(application) {
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    //private val repository: ProductLocalRepository = ProductLocalRepository(application)
    //private val container = (application as ProductApplication).appContainer
    //private val repository: ProductRepository = container.repository
    private val allProducts: LiveData<List<Product>>?
    private val searchResults: MutableLiveData<List<Product>>

    init {
        allProducts = repository.allProducts
        searchResults = repository.searchResults
    }

    fun insertProduct(product: Product) {
        repository.insertProduct(product)
    }

    fun findProduct(name: String) {
        repository.findProduct(name)
    }

    fun deleteProduct(name: String) {
        repository.deleteProduct(name)
    }

    fun getSearchResults(): MutableLiveData<List<Product>> {
        return searchResults
    }

    fun getAllProducts(): LiveData<List<Product>>? {
        return allProducts
    }

    /*companion object {
        /*val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                return MainViewModel(
                    (extras[APPLICATION_KEY] as ProductApplication)
                        .appContainer.repository
                ) as T
            }
        }*/
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val productRepository = (this[APPLICATION_KEY] as ProductApplication)
                    .appContainer.repository
                MainViewModel(repository = productRepository)
            }
        }
    }*/

}