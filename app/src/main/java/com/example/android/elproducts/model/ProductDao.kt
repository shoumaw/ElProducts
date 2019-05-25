package com.example.android.elproducts.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ProductDao {
    @get:Query("SELECT * FROM product")
    val all: List<Product>

    @Insert
    fun insertAll(vararg products: Product)
}