package com.example.android.elproducts.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.elproducts.R
import com.example.android.elproducts.injection.ViewModelFactory
import com.example.android.elproducts.viewModel.ProductListViewModel
/** A fragment to view to a list of products */
class ProductListFragment : Fragment() {
    private lateinit var binding: com.example.android.elproducts.databinding.FragmentProductListBinding
    private lateinit var viewModel: ProductListViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)
        binding.productList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        // Create viewModel through factory and bind it
        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity as AppCompatActivity )).get(
            ProductListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
            errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
        return binding.root
    }


    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}