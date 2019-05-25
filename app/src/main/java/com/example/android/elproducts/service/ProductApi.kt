package com.example.android.elproducts.service

import com.example.android.elproducts.model.Product
import io.reactivex.Observable
import retrofit2.http.GET

interface ProductApi {

    @GET("/")
    fun getProducts(): Observable<APIResponse>
}

class APIResponse {
    var data: List<Product>? = null
}
