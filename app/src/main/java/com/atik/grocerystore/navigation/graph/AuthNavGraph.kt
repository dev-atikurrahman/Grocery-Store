package com.atik.grocerystore.navigation.graph

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.atik.grocerystore.defaultEnterTransition
import com.atik.grocerystore.defaultExitTransition
import com.atik.grocerystore.feature.auth.presentation.login.LoginScreen
import com.atik.grocerystore.feature.auth.presentation.login.LoginViewModel.Companion.AUTH_TAG
import com.atik.grocerystore.navigation.AUTH_GRAPH_ROUTE
import com.atik.grocerystore.navigation.HOME_GRAPH_ROUTE
import com.atik.grocerystore.navigation.Routes


fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Routes.Login.route,
        route = AUTH_GRAPH_ROUTE
    ) {
        composable(
            Routes.Login.route,
            enterTransition = defaultEnterTransition,
            exitTransition = defaultExitTransition,
            popEnterTransition = defaultEnterTransition,
            popExitTransition = defaultExitTransition
        ) {
            LoginScreen(
                onLoginSuccess = {
                    Log.d(
                        AUTH_TAG,
                        "onLoginSuccess: navigating to HOME_GRAPH_ROUTE, current backstack=${navController.currentBackStackEntry?.destination?.route}"
                    )
                    navController.navigate(HOME_GRAPH_ROUTE) {
                        popUpTo(AUTH_GRAPH_ROUTE) { inclusive = false  }
                        launchSingleTop = true
                    }
                    Log.d(
                        AUTH_TAG,
                        "onLoginSuccess: navigate() call returned, new current=${navController.currentDestination?.route}"
                    )
                }
            )
        }


    }
}
