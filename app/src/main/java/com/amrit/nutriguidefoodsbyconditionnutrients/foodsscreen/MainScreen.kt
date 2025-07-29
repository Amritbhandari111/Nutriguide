package com.amrit.nutriguidefoodsbyconditionnutrients.foodsscreen

import android.app.Application

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.amrit.nutriguidefoodsbyconditionnutrients.R
import com.amrit.nutriguidefoodsbyconditionnutrients.appcomponents.TopBar
import com.amrit.nutriguidefoodsbyconditionnutrients.popular.PopularScreen
import com.amrit.nutriguidefoodsbyconditionnutrients.popular.FoodViewModel
import com.amrit.nutriguidefoodsbyconditionnutrients.popular.FoodViewModelFactory
import com.amrit.nutriguidefoodsbyconditionnutrients.popular.GoalFoodsScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    // ✅ Removed "foods"
    val items = listOf("popular")

    var hasShownInitialLoader by rememberSaveable { mutableStateOf(false) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val categoryNameArg = navBackStackEntry?.arguments?.getString("categoryName")

    val showBackButton = currentRoute != null && currentRoute != "popular"

    val topBarTitle = when {
        currentRoute?.startsWith("foods/") == true && categoryNameArg != null ->
            categoryNameArg.limit(13)
        currentRoute?.startsWith("searchResult") == true ->
            navBackStackEntry?.arguments?.getString("foodName")?.limit(13) ?: "Nutrition App"
        else -> "Nutrition App"
    }

    Scaffold(
        topBar = {
            TopBar(
                title = topBarTitle,
                onBackClick = if (showBackButton) {
                    { navController.popBackStack() }
                } else null,
              //  onSearchClick = { navController.navigate("search") },
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            val icon = when (screen) {
                                "popular" -> R.drawable.ic_popular
                                else -> R.drawable.ic_foods
                            }
                            Icon(
                                painter = painterResource(id = icon),
                                contentDescription = screen,
                                tint = Color.Unspecified
                            )
                        },
                        label = { Text(screen.replaceFirstChar { it.uppercase() }) },
                        selected = currentRoute == screen,
                        onClick = {
                            if (currentRoute != screen) {
                                navController.navigate(screen) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            // ✅ Updated start destination to "popular"
            startDestination = "popular",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("popular") {
                PopularScreen(navController)
            }

            composable("goalFoods/{goal}") { backStackEntry ->
                val goal = backStackEntry.arguments?.getString("goal") ?: ""
                val viewModel: FoodViewModel = viewModel(
                    factory = FoodViewModelFactory(LocalContext.current.applicationContext as Application)
                )
                GoalFoodsScreen(goal = goal, viewModel = viewModel)
            }
        }
    }
}


fun String.limit(maxLength: Int): String {
    return if (this.length <= maxLength) this else this.take(maxLength) + "..."
}




