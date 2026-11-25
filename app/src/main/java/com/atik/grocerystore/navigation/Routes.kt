package com.atik.grocerystore.navigation

const val ROOT_GRAPH_ROUTE = "root_graph"
const val AUTH_GRAPH_ROUTE = "auth_graph"
const val HOME_GRAPH_ROUTE = "home_graph"

sealed class Routes(val route: String) {

    data object Login : Routes(route = "login")

    /** Home graph*/
    data object Home : Routes(route = "home_screen")
    data object Sales : Routes(route = "sales_screen")
    data object Products : Routes(route = "products_screen")
    data object Customers : Routes(route = "customers_screen")

}