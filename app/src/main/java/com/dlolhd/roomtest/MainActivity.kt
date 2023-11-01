package com.dlolhd.roomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dlolhd.roomtest.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapter: ProductListAdapter? = null
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listenerSetup()
        observerSetup()
        recyclerSetup()
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
                binding.tvNotAssignedId.text = getString(R.string.incomplete_information)
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
                    binding.tvNotAssignedId.text = String.format(Locale.US, "%d", it[0].id)
                    binding.productName.setText(it[0].productName)
                    binding.productQuantity.setText(
                        String.format(
                            Locale.US, "%d",
                            it[0].quantity
                        )
                    )
                } else {
                    binding.tvNotAssignedId.text = getString(R.string.no_match)
                }
            }
        }
    }

    private fun recyclerSetup() {

    }

    private fun clearFields() {
        binding.tvNotAssignedId.text = ""
        binding.productName.setText("")
        binding.productQuantity.setText("")
    }
}
















