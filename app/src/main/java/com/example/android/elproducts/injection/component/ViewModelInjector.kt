package com.example.android.elproducts.injection.component

import com.example.android.elproducts.injection.module.NetworkModule
import com.example.android.elproducts.viewModel.ProductListViewModel
import com.example.android.elproducts.viewModel.ProductViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(productListViewModel: ProductListViewModel)

    fun inject(productViewModel: ProductViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}