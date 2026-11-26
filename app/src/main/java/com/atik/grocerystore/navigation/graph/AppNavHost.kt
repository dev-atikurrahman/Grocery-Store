package com.atik.grocerystore.navigation.graph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.material.icons.rounded.AddBox
import androidx.compose.material.icons.rounded.Dashboard
import androidx.compose.material.icons.rounded.Groups
import androidx.compose.material.icons.rounded.Sell
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.atik.grocerystore.R
import com.atik.grocerystore.defaultEnterTransition
import com.atik.grocerystore.defaultExitTransition
import com.atik.grocerystore.feature.splash.StartDestination
import com.atik.grocerystore.navigation.AUTH_GRAPH_ROUTE
import com.atik.grocerystore.navigation.HOME_GRAPH_ROUTE
import com.atik.grocerystore.navigation.ROOT_GRAPH_ROUTE
import com.atik.grocerystore.navigation.Routes
import com.atik.grocerystore.navigation.bottombar.BottomNavigationBarSection
import com.atik.grocerystore.navigation.bottombar.NavigationItem
import com.atik.grocerystore.ui.theme.GlassBackdropFallback
import com.kyant.backdrop.backdrops.layerBackdrop
import com.kyant.backdrop.backdrops.rememberLayerBackdrop


private const val SPLASH_ROOT_ROUTE = "splash_root"

@Composable
fun AppNavHost(
    startDestination: StartDestination,
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()
    val backdrop = rememberLayerBackdrop {
        drawRect(GlassBackdropFallback)
        drawContent()
    }

    val startRoute = when (startDestination) {
        StartDestination.Home -> HOME_GRAPH_ROUTE
        StartDestination.Login,
        StartDestination.Loading -> AUTH_GRAPH_ROUTE
    }

    val navigationItems = listOf(
        NavigationItem(
            label = stringResource(R.string.dashboard),
            route = Routes.Home.route,
            selectedIcon = Icons.Rounded.Dashboard,
            unSelectedIcon = Icons.Outlined.Dashboard,
        ),
        NavigationItem(
            label = stringResource(R.string.sales),
            route = Routes.Sales.route,
            selectedIcon = Icons.Rounded.Sell,
            unSelectedIcon = Icons.Outlined.Sell,
            badgeCount = 4
        ),
        NavigationItem(
            label = stringResource(R.string.products),
            route = Routes.Products.route,
            selectedIcon = Icons.Rounded.AddBox,
            unSelectedIcon = Icons.Outlined.AddBox,
        ),
        NavigationItem(
            label = stringResource(R.string.customers),
            route = Routes.Customers.route,
            selectedIcon = Icons.Rounded.Groups,
            unSelectedIcon = Icons.Outlined.Groups,
        )
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val selectedIndex = navigationItems.indexOfFirst { it.route == currentRoute }
    val showBottomBar = selectedIndex != -1

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary,
        contentWindowInsets = WindowInsets(0),
        bottomBar = {
            BottomNavigationBarSection(
                showBottomBar = showBottomBar,
                navController = navController,
                items = navigationItems,
                selectedIndex = selectedIndex,
                backdrop = backdrop
            )
        }
    ) { _ ->
        Box(modifier = Modifier
            .fillMaxSize()
            .layerBackdrop(backdrop)) {
            NavHost(
                navController = navController,
                startDestination = startRoute,
                route = ROOT_GRAPH_ROUTE,
                modifier = modifier.fillMaxSize(),
                enterTransition = defaultEnterTransition,
                exitTransition = defaultExitTransition,
                popEnterTransition = defaultEnterTransition,
                popExitTransition = defaultExitTransition,
            ) {

                authNavGraph(navController)
                homeNavGraph()
            }

        }

    }


}