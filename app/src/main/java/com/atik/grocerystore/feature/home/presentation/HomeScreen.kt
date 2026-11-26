package com.atik.grocerystore.feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atik.grocerystore.R
import com.atik.grocerystore.components.LoadingOverlay
import com.atik.grocerystore.ui.background.BackgroundColors
import com.atik.grocerystore.ui.background.Glow
import com.atik.grocerystore.ui.background.GlowBackground
import com.atik.grocerystore.ui.background.GradientBackground
import com.atik.grocerystore.ui.background.PastelBackground
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        uiState = uiState
    )

}

@Composable
fun HomeScreenContent(
    uiState: HomeScreenUiState,
) {
    val BottomBarContentPadding = 68.dp + 32.dp
    LoadingOverlay(
        isLoading = uiState.isLoading,
        message = stringResource(R.string.loading_message)
    ) {
        GlowBackground(
            modifier = Modifier.fillMaxSize(),
            baseColor = BackgroundColors.SoftLavender,
            glows = listOf(
                Glow(
                    color = BackgroundColors.PurpleGlow,
                    centerX = 0.2f,
                    centerY = 0.2f,
                    radius = 0.6f
                ),
                Glow(
                    color = BackgroundColors.PinkGlow,
                    centerX = 0.8f,
                    centerY = 0.7f,
                    radius = 0.5f
                )
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .padding(
                        bottom = BottomBarContentPadding +
                                WindowInsets.navigationBars.asPaddingValues()
                                    .calculateBottomPadding()
                    )
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Home")
            }
        }

    }
}