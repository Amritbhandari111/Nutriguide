package com.amrit.nutriguidefoodsbyconditionnutrients

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.BackHandler
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import com.amrit.nutriguidefoodsbyconditionnutrients.appcomponents.ExitConfirmationDialog
import com.amrit.nutriguidefoodsbyconditionnutrients.foodsscreen.MainScreen
import com.amrit.nutriguidefoodsbyconditionnutrients.ui.theme.NutriGuideFoodsByConditionNutrientsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NutriGuideFoodsByConditionNutrientsTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    var showExitDialog by remember { mutableStateOf(false) }

                    // ✅ Back button handler for exit dialog
                    BackHandler {
                        showExitDialog = true
                    }

                    // Your main screen
                    MainScreen()

                    // ✅ Exit confirmation dialog
                    if (showExitDialog) {
                        ExitConfirmationDialog(
                            onConfirm = { finish() }, // Exit app
                            onDismiss = { showExitDialog = false }
                        )
                    }
                }
            }
        }
    }
}
