package com.atik.grocerystore.navigation.graph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.atik.grocerystore.navigation.HOME_GRAPH_ROUTE
import com.atik.grocerystore.navigation.Routes

fun NavGraphBuilder.homeNavGraph() {
    navigation(
        startDestination = Routes.Home.route,
        route = HOME_GRAPH_ROUTE
    ) {
        composable(Routes.Home.route) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Home")
            }
        }
    }
}