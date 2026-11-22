package com.atik.grocerystore

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atik.grocerystore.feature.auth.data.preferences.UserPreferences
import com.atik.grocerystore.feature.splash.SplashViewModel
import com.atik.grocerystore.feature.splash.StartDestination
import com.atik.grocerystore.navigation.graph.AppNavHost
import com.atik.grocerystore.ui.theme.GroceryStoreTheme
import com.atik.grocerystore.ui.theme.ThemeMode
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val userPreferences: UserPreferences by inject()
    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )

        splashScreen.setKeepOnScreenCondition {
            splashViewModel.startDestination.value is StartDestination.Login
        }
        setContent {
            GroceryStoreTheme{
                val startDestination by splashViewModel.startDestination.collectAsStateWithLifecycle()
                AppNavHost(startDestination = startDestination)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GroceryStoreTheme {}
}