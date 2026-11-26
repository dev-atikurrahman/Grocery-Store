package com.atik.grocerystore.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.atik.grocerystore.CustomerScreen
import com.atik.grocerystore.ProductScreen
import com.atik.grocerystore.SellScreen
import com.atik.grocerystore.feature.home.presentation.HomeScreen
import com.atik.grocerystore.navigation.HOME_GRAPH_ROUTE
import com.atik.grocerystore.navigation.Routes

fun NavGraphBuilder.homeNavGraph(

) {
    navigation(
        startDestination = Routes.Home.route,
        route = HOME_GRAPH_ROUTE
    ) {
        composable(Routes.Home.route) { HomeScreen() }
        composable(Routes.Sales.route) { SellScreen() }
        composable(Routes.Products.route) { ProductScreen() }
        composable(Routes.Customers.route) { CustomerScreen() }
    }
}