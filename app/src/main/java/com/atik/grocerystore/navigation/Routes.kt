package com.atik.grocerystore.navigation

const val ROOT_GRAPH_ROUTE = "root_graph"
const val AUTH_GRAPH_ROUTE = "auth_graph"
const val HOME_GRAPH_ROUTE = "home_graph"

sealed class Routes (val route: String) {

    data object Onboarding : Routes(route = "onboarding")

    data object Login : Routes(route = "login")

    data object Home : Routes(route = "home")
}