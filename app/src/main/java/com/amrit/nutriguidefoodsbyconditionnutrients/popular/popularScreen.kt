package com.amrit.nutriguidefoodsbyconditionnutrients.popular

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.amrit.nutriguidefoodsbyconditionnutrients.R

data class GoalCard(val imageResId: Int, val title: String)

// ✅ Replace these drawables with real icons from your `res/drawable` folder
val goalBasedCards = listOf(
    GoalCard(R.drawable.fatloss, "Fat Loss Foods"),
    GoalCard(R.drawable.weightloss, "Weight Loss Foods"),
    GoalCard(R.drawable.weightgain, "Weight Gain Foods"),
    GoalCard(R.drawable.energy, "High Energy Foods"),
    GoalCard(R.drawable.musclegain, "Muscle Gain Foods"),
    GoalCard(R.drawable.lean, "Lean Body Foods"),
    GoalCard(R.drawable.preworkout, "Pre-Workout Foods"),
    GoalCard(R.drawable.postworkout, "Post-Workout Foods"),
    GoalCard(R.drawable.lightdinner, "Light Dinner Foods")
)


@Composable
fun PopularScreen(navController: NavController) {
    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(goalBasedCards) { item ->
                    GoalCardView(item, navController)
                }
            }
        }
    }
}


@Composable
fun GoalCardView(item: GoalCard,navController: NavController) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF005A9C))
            .clickable {
                navController.navigate("goalFoods/${Uri.encode(item.title)}")

            }
            .padding(bottom = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = item.title,
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
