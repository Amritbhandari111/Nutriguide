package com.amrit.nutriguidefoodsbyconditionnutrients.popular



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.amrit.nutriguidefoodsbyconditionnutrients.foodsscreen.FoodItem
import com.amrit.nutriguidefoodsbyconditionnutrients.foodsscreen.NutrientCell

@Composable
fun FoodItemRowWithHighlight(food: FoodItem, highlight: String = "") {
    val highlightedName = if (highlight.isNotBlank()) {
        buildAnnotatedString {
            val index = food.name.indexOf(highlight, ignoreCase = true)
            if (index >= 0) {
                append(food.name.substring(0, index))
                withStyle(style = SpanStyle(background = Color.Yellow, fontWeight = FontWeight.Bold)) {
                    append(food.name.substring(index, index + highlight.length))
                }
                append(food.name.substring(index + highlight.length))
            } else {
                append(food.name)
            }
        }
    } else {
        buildAnnotatedString { append(food.name) }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = highlightedName,
            modifier = Modifier.weight(1.5f),
            style = MaterialTheme.typography.bodyMedium
        )

        VerticalDivider()

        NutrientCell("${food.protein}g", "Prot.")
        VerticalDivider()
        NutrientCell("${food.fat}g", "Fat")
        VerticalDivider()
        NutrientCell("${food.carb}g", "Carb")
        VerticalDivider()
        NutrientCell("${food.kcal} kcal", "Kcal")
    }
}

@Composable
fun VerticalDivider() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(1.dp)
            .padding(horizontal = 4.dp)
            .background(Color.LightGray)
    )
}
