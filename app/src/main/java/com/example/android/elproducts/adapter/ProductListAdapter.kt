package com.example.android.elproducts.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.app.AppCompatActivity
import com.example.android.elproducts.R
import com.example.android.elproducts.databinding.ItemProductBinding
import com.example.android.elproducts.fragment.ProductDetailFragment
import com.example.android.elproducts.model.Product
import com.example.android.elproducts.viewModel.ProductViewModel

/** A product list recycler view adapter to bind the retrieved products to the view holders*/
class ProductListAdapter: RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    private lateinit var productList:List<Product>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: com.example.android.elproducts.databinding.ItemProductBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_product, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return if(::productList.isInitialized) productList.size else 0
    }

    fun updateProductList(productList:List<Product>){
        this.productList = productList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemProductBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = ProductViewModel()
        private lateinit var product : Product

        fun bind(product:Product){
            this.product = product
            viewModel.bind(product)
            binding.viewModel = viewModel
            binding.cardView.setOnClickListener {
                val activity = it.context as AppCompatActivity
                val productDetailFragment = ProductDetailFragment.newInstance(product)
                activity.supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, productDetailFragment).addToBackStack(null).commit()
            }

        }
    }
}