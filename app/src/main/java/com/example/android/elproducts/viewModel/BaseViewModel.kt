package com.example.android.elproducts.viewModel

import android.arch.lifecycle.ViewModel
import com.example.android.elproducts.injection.component.DaggerViewModelInjector
import com.example.android.elproducts.injection.component.ViewModelInjector
import com.example.android.elproducts.injection.module.NetworkModule


abstract class BaseViewModel:ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }


    private fun inject() {
        when (this) {
            is ProductListViewModel -> injector.inject(this)
            is ProductViewModel -> injector.inject(this)
        }
    }
}