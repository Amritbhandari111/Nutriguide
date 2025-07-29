package com.amrit.nutriguidefoodsbyconditionnutrients.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.amrit.nutriguidefoodsbyconditionnutrients.foodsscreen.FoodItem
import com.amrit.nutriguidefoodsbyconditionnutrients.foodsscreen.FoodItemRow

@Composable
fun GoalFoodListScreenSimple(goal: String, foodItems: List<FoodItem>) {
    // ✅ Filter only based on categoryName (no nutrient logic now)
    val filteredItems = remember(goal, foodItems) {
        foodItems.filter { it.categoryName.equals(goal, ignoreCase = true) }
    }

    if (filteredItems.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No matching foods found for this goal.",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(1.dp)
        ) {
            items(filteredItems) { item ->
                FoodItemRow(item)
            }
        }
    }
}
