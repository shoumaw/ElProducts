package com.example.android.elproducts.fragment

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.elproducts.R
import com.example.android.elproducts.model.Product
import com.example.android.elproducts.viewModel.ProductDetailViewModel

/** A fragment to view a product's details */
class ProductDetailFragment : Fragment() {
    private lateinit var binding: com.example.android.elproducts.databinding.FragmentProductDetailBinding
    private lateinit var viewModel: ProductDetailViewModel
    private lateinit var product : Product
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        arguments?.getSerializable("Product")?.let {
            product = it as Product
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View{
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false)
        viewModel = ProductDetailViewModel()
        viewModel.bind(product)
        binding.viewModel = viewModel
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(product: Product) = ProductDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable("Product", product)
            }
        }
    }
}