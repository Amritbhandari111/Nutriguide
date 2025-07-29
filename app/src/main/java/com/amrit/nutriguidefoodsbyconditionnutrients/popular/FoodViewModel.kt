package com.amrit.nutriguidefoodsbyconditionnutrients.popular


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.amrit.nutriguidefoodsbyconditionnutrients.foodsscreen.FoodItem




import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch

open class FoodViewModel(application: Application) : AndroidViewModel(application) {

    private val _foodList = MutableStateFlow<List<FoodItem>>(emptyList())
    open val foodList: StateFlow<List<FoodItem>> = _foodList  // Initialize here

    init {
        viewModelScope.launch {
            val data = loadFoodJson(application)
            _foodList.value = data
        }
    }
}

