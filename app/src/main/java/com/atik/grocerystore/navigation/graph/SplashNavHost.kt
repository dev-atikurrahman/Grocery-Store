package com.atik.grocerystore.navigation.graph

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.atik.grocerystore.components.AppLoadingIndicator
import com.atik.grocerystore.feature.auth.presentation.login.LoginViewModel.Companion.AUTH_TAG
import com.atik.grocerystore.feature.onboarding.presentation.OnboardingScreen
import com.atik.grocerystore.feature.splash.SplashViewModel
import com.atik.grocerystore.feature.splash.StartDestination
import com.atik.grocerystore.navigation.AUTH_GRAPH_ROUTE
import com.atik.grocerystore.navigation.HOME_GRAPH_ROUTE
import com.atik.grocerystore.navigation.Routes
import org.koin.androidx.compose.koinViewModel


private const val SPLASH_ROOT_ROUTE = "splash_root"

@Composable
fun SplashNavHost(
    splashViewModel: SplashViewModel = koinViewModel()
) {

    val startDestination by splashViewModel
        .startDestination
        .collectAsStateWithLifecycle()


    if (startDestination == StartDestination.Loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AppLoadingIndicator(size = 32.dp)
        }
    } else {
        val navController = rememberNavController()
        val actualStartDestination = when (startDestination) {
            StartDestination.Onboarding -> Routes.Onboarding.route
            StartDestination.Login -> AUTH_GRAPH_ROUTE
            StartDestination.Home -> HOME_GRAPH_ROUTE
            StartDestination.Loading -> Routes.Onboarding.route
        }
        Log.d(AUTH_TAG, "Initial destination: $actualStartDestination")

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            contentWindowInsets = WindowInsets.safeDrawing,
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = actualStartDestination,
                route = SPLASH_ROOT_ROUTE,
                modifier = Modifier.padding(innerPadding)
            ) {

                composable(Routes.Onboarding.route) {

                    OnboardingScreen(
                        onFinishOnboarding = {

                            navController.navigate(AUTH_GRAPH_ROUTE) {
                                popUpTo(Routes.Onboarding.route) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                    )
                }

                navigation(
                    startDestination = Routes.Login.route,
                    route = AUTH_GRAPH_ROUTE
                ) {

                }

            }
        }
    }


}