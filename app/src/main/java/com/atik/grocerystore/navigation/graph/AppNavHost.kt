package com.atik.grocerystore.navigation.graph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.atik.grocerystore.defaultEnterTransition
import com.atik.grocerystore.defaultExitTransition
import com.atik.grocerystore.feature.onboarding.presentation.OnboardingScreen
import com.atik.grocerystore.feature.splash.StartDestination
import com.atik.grocerystore.navigation.AUTH_GRAPH_ROUTE
import com.atik.grocerystore.navigation.HOME_GRAPH_ROUTE
import com.atik.grocerystore.navigation.ROOT_GRAPH_ROUTE
import com.atik.grocerystore.navigation.Routes


private const val SPLASH_ROOT_ROUTE = "splash_root"

@Composable
fun AppNavHost(
    startDestination: StartDestination,
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    val startRoute = when (startDestination) {
        StartDestination.Login -> HOME_GRAPH_ROUTE
        StartDestination.Home -> AUTH_GRAPH_ROUTE
        StartDestination.Onboarding,
        StartDestination.Loading -> Routes.Onboarding.route
    }

    NavHost(
        navController = navController,
        startDestination = startRoute,
        route = ROOT_GRAPH_ROUTE,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        enterTransition = defaultEnterTransition,
        exitTransition = defaultExitTransition,
        popEnterTransition = defaultEnterTransition,
        popExitTransition = defaultExitTransition,
    ) {

        composable(Routes.Onboarding.route) {
            OnboardingScreen(
                onFinished = {
                    navController.navigate(AUTH_GRAPH_ROUTE) {
                        popUpTo(Routes.Onboarding.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        authNavGraph(navController)
        homeNavGraph()
    }


}