package com.example.android.elproducts.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.android.elproducts.model.Product
import com.example.android.elproducts.model.ProductDao


@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}