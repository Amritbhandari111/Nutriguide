package com.amrit.nutriguidefoodsbyconditionnutrients.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.amrit.nutriguidefoodsbyconditionnutrients.foodsscreen.FoodItem
import com.amrit.nutriguidefoodsbyconditionnutrients.foodsscreen.FoodItemRow
import kotlinx.coroutines.delay

@Composable
fun GoalFoodsScreen(goal: String, viewModel: FoodViewModel) {
    val foodList by viewModel.foodList.collectAsState()
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var isLoading by remember { mutableStateOf(true) }

    val filteredItems = remember(goal, foodList, searchQuery.text) {
        foodList.filter {
            it.categoryName.equals(goal, ignoreCase = true) &&
                    it.name.contains(searchQuery.text, ignoreCase = true)
        }
    }

    // Simulated loading delay
    LaunchedEffect(Unit) {
        delay(500)
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .background(Color.White)
    ) {
        // 🔍 Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search in $goal") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            singleLine = true,
            trailingIcon = {
                if (searchQuery.text.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = TextFieldValue("") }) {
                        Icon(Icons.Default.Close, contentDescription = "Clear")
                    }
                }
            }
        )

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else if (filteredItems.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No matching foods found", color = Color.Gray)
            }
        } else {
            LazyColumn {
                itemsIndexed(filteredItems) { _, item ->
                    val isHighlighted = item.name.contains(searchQuery.text, ignoreCase = true)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(if (isHighlighted && searchQuery.text.isNotEmpty()) Color(0xFFFFF59D) else Color.Transparent)
                    ) {
                        FoodItemRow(item)
                    }
                }
            }
        }
    }
}
