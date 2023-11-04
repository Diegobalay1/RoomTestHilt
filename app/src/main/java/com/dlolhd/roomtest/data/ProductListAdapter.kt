package com.dlolhd.roomtest.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dlolhd.roomtest.R
import javax.inject.Inject

class ProductListAdapter @Inject constructor(
    //private val productItemLayout: Int
) : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    private var productList: List<Product>? = null


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = holder.item
        productList.let { products ->
            item.text = products!![position].productName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            //productItemLayout, parent, false
            R.layout.product_list_item, parent, false
        )
        return ProductViewHolder(view)
    }

    fun setProductList(products: List<Product>) {
        productList = products
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (productList == null) 0 else productList!!.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item: TextView = itemView.findViewById(R.id.product_row)
    }

}















