package com.example.flash.data

import android.content.ClipData.Item
import androidx.annotation.StringRes
import com.example.flash.R

object DataSource {
 fun loadCategories():List<Categories>{
     return listOf<Categories>(
         Categories(stringResourceId = R.string.fresh_fruits, imageResourceId = R.drawable.fruits ),
         Categories(R.string.bath_body, R.drawable.bathbody),
         Categories(R.string.bread_biscuits, R.drawable.breadbiscuits),
         Categories(R.string.kitchen_essentials, R.drawable.kitchen),
         Categories(R.string.munchies, R.drawable.munchies),
         Categories(R.string.packaged_food, R.drawable.packaged),
         Categories(R.string.stationary, R.drawable.stationery),
         Categories(R.string.pet_food, R.drawable.pet),
         Categories(R.string.sweet_tooth, R.drawable.sweet),
         Categories(R.string.vegetables, R.drawable.vegetables),
         Categories(R.string.beverages, R.drawable.beverages)

     )
 }
    fun loadItems(
        @StringRes categoryName: Int
    ): List<Items>{
        return listOf(
            Items(R.string.banana_robusta, R.string.fresh_fruits, "1kg",100,R.drawable.banana ),
            Items(R.string.shimla_apple, R.string.fresh_fruits, "1kg",250,R.drawable.apples ),
            Items(R.string.papaya_semi_ripe, R.string.fresh_fruits, "1kg",150,R.drawable.papaya ),
            Items(R.string.pomegranate, R.string.fresh_fruits, "500kg",150,R.drawable.pomegranate ),
            Items(R.string.pineapple, R.string.fresh_fruits, "1kg",130,R.drawable.pineapple ),
            Items(R.string.pepsi_can, R.string.beverages, "1",40,R.drawable.pepsi )
        ).filter{
            it.itemCategoryId == categoryName
        }
    }
}