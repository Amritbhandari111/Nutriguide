package com.amrit.nutriguidefoodsbyconditionnutrients.foodsscreen

data class FoodItem(
    val name: String,
    val protein: Float,
    val fat: Float,
    val carb: Float,
    val kcal: Int,
    val categoryName: String
)
