package com.amrit.nutriguidefoodsbyconditionnutrients.foodsscreen
import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.*

import androidx.compose.material3.Divider
import androidx.compose.material3.Text

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp




@Composable
fun FoodItemRow(item: FoodItem) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        // Food name
        Text(
            text = "${item.name} (100 g.)",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 15.sp,
                color = Color.Gray,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 6.dp)
        )

        // Nutrient values
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NutrientCell("${item.protein} g", "proteins", Modifier.weight(1f))
            NutrientCell("${item.fat} g", "fat", Modifier.weight(1f))
            NutrientCell("${item.carb} g", "carb", Modifier.weight(1f))
            NutrientCell("${item.kcal}", "Kcal.", Modifier.weight(1f))
        }

        Divider(modifier = Modifier.padding(top = 8.dp), thickness = 0.5.dp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFoodItemRow() {
    FoodItemRow(
        item = FoodItem(
            name = "Grilled Chicken",
            protein = 27.5f,
            fat = 3.6f,
            carb = 0.0f,
            kcal = 165,
            categoryName = "Poultry Products"

        )
    )
}
@Composable
fun NutrientCell(value: String, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = value, fontSize = 15.sp, color = Color.Black)
        Text(text = label, fontSize = 12.sp, color = Color.Gray)
    }
}

