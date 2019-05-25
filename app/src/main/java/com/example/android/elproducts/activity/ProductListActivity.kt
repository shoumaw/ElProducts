package com.example.android.elproducts.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.android.elproducts.R
import com.example.android.elproducts.fragment.ProductListFragment

/** Main activity of the application which will start by launching a product list fragment*/
class ProductListActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        launchFragment(ProductListFragment(),false)

    }

     fun launchFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragmentContainer, fragment, "")

        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commitAllowingStateLoss()
    }

}