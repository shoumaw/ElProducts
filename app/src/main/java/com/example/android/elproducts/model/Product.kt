package com.example.android.elproducts.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverter
import java.io.Serializable

@Entity
data class Product(
        @field:PrimaryKey
        val id: Int,
        val name: String,
        val productDescription: String,
        @Embedded
        val image : Image,
        val price: Int
) : Serializable
data class Image(
        val link: String,
        val height: Int,
        val width: Int
)