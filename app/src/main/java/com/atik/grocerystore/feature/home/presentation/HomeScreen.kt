package com.atik.grocerystore.feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atik.grocerystore.R
import com.atik.grocerystore.components.GlassBackground
import com.atik.grocerystore.components.LoadingOverlay
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
        GlassBackground(
            backgroundRes = R.drawable.bg
        ) { backdrop ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .padding(bottom = BottomBarContentPadding +
                            WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding())
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Home")
            }
        }

    }
}