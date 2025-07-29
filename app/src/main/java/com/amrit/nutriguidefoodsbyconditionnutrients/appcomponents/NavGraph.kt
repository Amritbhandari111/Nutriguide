package com.amrit.nutriguidefoodsbyconditionnutrients.appcomponents



import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amrit.nutriguidefoodsbyconditionnutrients.popular.FoodViewModel
import com.amrit.nutriguidefoodsbyconditionnutrients.popular.GoalFoodsScreen
import com.amrit.nutriguidefoodsbyconditionnutrients.popular.PopularScreen


@Composable
fun AppNavGraph(navController: NavHostController, viewModel: FoodViewModel) {
    NavHost(navController = navController, startDestination = "popular") {
        composable("popular") {
            // Replace with your new start screen, like PopularScreen
            PopularScreen(navController)
        }
        composable("goalFoods/{goal}") { backStackEntry ->
            val goal = backStackEntry.arguments?.getString("goal") ?: ""
            GoalFoodsScreen(goal = goal, viewModel = viewModel)
        }
    }
}

