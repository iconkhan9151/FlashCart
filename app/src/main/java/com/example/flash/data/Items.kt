package com.example.flash.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Items(
   @StringRes val stringResourceId: Int,
   @StringRes val itemCategoryId: Int,
    val itemQuantityId: String,
    val itemPriceId: Int,
   @DrawableRes val imageResourceId: Int
)
