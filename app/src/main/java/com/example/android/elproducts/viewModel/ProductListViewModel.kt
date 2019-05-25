package com.example.android.elproducts.viewModel

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.android.elproducts.R
import com.example.android.elproducts.adapter.ProductListAdapter
import com.example.android.elproducts.model.Product
import com.example.android.elproducts.model.ProductDao
import com.example.android.elproducts.service.ProductApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ProductListViewModel(private val productDao: ProductDao):BaseViewModel(){
    @Inject
    lateinit var productApi: ProductApi
    val productListAdapter: ProductListAdapter = ProductListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadProducts() }

    private lateinit var subscription: Disposable

    init{
        loadProducts()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadProducts(){
        subscription = Observable.fromCallable { productDao.all }
                .concatMap {
                    dbProductList ->
                        if(dbProductList.isEmpty())
                            productApi.getProducts().concatMap {
                                            apiProductList -> productDao.insertAll(*apiProductList.data!!.toTypedArray())
                                 Observable.just(apiProductList)
                                       }
                        else
                            Observable.just(dbProductList)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveProductListStart() }
                .doOnTerminate { onRetrieveProductListFinish() }
                .subscribe(
                        { result -> onRetrieveProductListSuccess(result as List<Product>)},
                        { onRetrieveProductListError() }
                )
    }

    private fun onRetrieveProductListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveProductListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveProductListSuccess(productList:List<Product>){
        productListAdapter.updateProductList(productList)
    }

    private fun onRetrieveProductListError(){
        errorMessage.value = R.string.post_error
    }
}