package com.example.android.elproducts.adapter

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.android.elproducts.R
import com.example.android.elproducts.utils.getParentActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

/** Adapters for binding values to our views*/
@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View,  visibility: MutableLiveData<Int>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}

@BindingAdapter("productText")
fun setProductText(view: TextView,  text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("productPrice")
fun setProductPrice(view: TextView,  text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = "$$value"})
    }
}

@BindingAdapter("imageLink", "imageHeight", "imageWidth")
fun setProductImage(view: ImageView, url: MutableLiveData<String>?, height: MutableLiveData<Int>, width: MutableLiveData<Int> ) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && url != null) {
        url.observe(parentActivity, Observer { value ->
            if (value != null) {
                Picasso.get().load(value).error(R.drawable.error).resize(width.value!!,height.value!!).into(view,  object :
                    Callback {
                    override fun onError(e: Exception?) {
                        //handle error
                    }

                    override fun onSuccess() {
                        //do extra stuff on success

                    }

                })
            }
        })
    }
}