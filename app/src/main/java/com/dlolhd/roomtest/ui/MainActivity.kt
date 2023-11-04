package com.dlolhd.roomtest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dlolhd.roomtest.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.dlolhd.roomtest.data.Product
import com.dlolhd.roomtest.data.ProductListAdapter
import com.dlolhd.roomtest.R
import dagger.hilt.android.AndroidEntryPoint

import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //private var adapter: ProductListAdapter? = null
    //@Inject var adapter: ProductListAdapter? = null
    //private val viewModel: MainViewModel by viewModels()
    //private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }
    private lateinit var viewModel: MainViewModel
    @Inject lateinit var lm: LayoutManager
    @Inject lateinit var adapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelSetup()
        listenerSetup()
        observerSetup()
        recyclerSetup()
    }

    private fun viewModelSetup() {
        val vm : MainViewModel by viewModels()
        viewModel = vm
    }

    private fun listenerSetup() {
        binding.addButton.setOnClickListener {
            val name = binding.productName.text.toString()
            val quantity = binding.productQuantity.text.toString()

            if (name != "" && quantity != "") {
                val product = Product(name, Integer.parseInt(quantity))
                viewModel.insertProduct(product)
                clearFields()
            } else {
                binding.productId.text = getString(R.string.incomplete_information)
            }
        }

        binding.findButton.setOnClickListener {
            viewModel.findProduct(
                binding.productName.text.toString()
            )
        }

        binding.deleteButton.setOnClickListener {
            viewModel.deleteProduct(
                binding.productName.text.toString()
            )
            clearFields()
        }

    }

    private fun observerSetup() {
        viewModel.getAllProducts()?.observe(this) { products ->
            products?.let {
                adapter?.setProductList(it)
            }
        }

        viewModel.getSearchResults().observe(this) { products ->
            products?.let {
                if (it.isNotEmpty()) {
                    binding.productId.text = String.format(Locale.US, "%d", it[0].id)
                    binding.productName.setText(it[0].productName)
                    binding.productQuantity.setText(
                        String.format(
                            Locale.US, "%d",
                            it[0].quantity
                        )
                    )
                } else {
                    binding.productId.text = getString(R.string.no_match)
                }
            }
        }
    }

    private fun recyclerSetup() {
        binding.productRecycler.apply {
            //this@MainActivity.adapter = ProductListAdapter(R.layout.product_list_item)
            setHasFixedSize(true)
            layoutManager = lm
            adapter = this@MainActivity.adapter
            //adapter = this@MainActivity.adapter
        }
        /*adapter = ProductListAdapter(R.layout.product_list_item)
        binding.productRecycler.layoutManager = LinearLayoutManager(this)
        binding.productRecycler.adapter = adapter*/
    }

    private fun clearFields() {
        binding.productId.text = ""
        binding.productName.setText("")
        binding.productQuantity.setText("")
    }
}
















