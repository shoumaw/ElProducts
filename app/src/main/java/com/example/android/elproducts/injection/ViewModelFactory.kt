package com.example.android.elproducts.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import com.example.android.elproducts.database.AppDatabase
import com.example.android.elproducts.viewModel.ProductListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "products").build()
            @Suppress("UNCHECKED_CAST")
            return ProductListViewModel(db.productDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}