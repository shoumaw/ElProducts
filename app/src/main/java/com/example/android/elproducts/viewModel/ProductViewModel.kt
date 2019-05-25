package com.example.android.elproducts.viewModel

import android.arch.lifecycle.MutableLiveData
import com.example.android.elproducts.model.Product

class ProductViewModel:BaseViewModel() {
    private val productName = MutableLiveData<String>()
    private val productImageLink = MutableLiveData<String>()
    private val productImageHeight = MutableLiveData<Int>()
    private val productImageWidth = MutableLiveData<Int>()
    private val productPrice = MutableLiveData<String>()
    private val productDescription = MutableLiveData<String>()


    fun bind(product: Product){
        productName.value = product.name
        productPrice.value = product.price.toString()
        productDescription.value = product.productDescription
        productImageLink.value = product.image.link
        productImageHeight.value = product.image.height
        productImageWidth.value = product.image.width

    }

    fun getProductName():MutableLiveData<String>{
        return productName
    }

    fun getProductPrice():MutableLiveData<String>{
        return productPrice
    }

    fun getProductImageLink():MutableLiveData<String>{
        return productImageLink
    }

    fun getProductImageHeight():MutableLiveData<Int>{
        return productImageHeight
    }

    fun getProductImageWidth():MutableLiveData<Int>{
        return productImageWidth
    }
}