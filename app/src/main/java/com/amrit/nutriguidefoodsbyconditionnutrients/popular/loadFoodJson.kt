package com.amrit.nutriguidefoodsbyconditionnutrients.popular

import android.content.Context
import com.amrit.nutriguidefoodsbyconditionnutrients.foodsscreen.FoodItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun loadFoodJson(context: Context): List<FoodItem> {
    val jsonString = context.assets.open("popularfoods.json").bufferedReader().use { it.readText() }
    val type = object : TypeToken<Map<String, List<FoodItemWithoutCategory>>>() {}.type
    val rawMap: Map<String, List<FoodItemWithoutCategory>> = Gson().fromJson(jsonString, type)

    return rawMap.flatMap { (category, foods) ->
        foods.mapNotNull { food ->
            try {
                FoodItem(
                    name = food.name ?: "Unknown",
                    protein = food.protein ?: 0f,
                    fat = food.fat ?: 0f,
                    carb = food.carb ?: 0f,
                    kcal = food.kcal ?: 0,
                    categoryName = category
                )

            } catch (e: Exception) {
                null // Ignore any item that causes crash
            }
        }
    }

}

data class FoodItemWithoutCategory(
    val name: String?,
    val protein: Float?,
    val fat: Float?,
    val carb: Float?,
    val kcal: Int?
)

