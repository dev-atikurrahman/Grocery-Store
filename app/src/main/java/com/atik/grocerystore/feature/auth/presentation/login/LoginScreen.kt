package com.atik.grocerystore.feature.auth.presentation.login


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.atik.grocerystore.R
import com.atik.grocerystore.components.GlassBackground
import com.atik.grocerystore.components.GlassCard
import com.atik.grocerystore.components.GlassPill
import com.atik.grocerystore.components.LoadingOverlay
import com.atik.grocerystore.ui.theme.GlassPillFill
import com.atik.grocerystore.ui.theme.GlassTextPrimary
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {

    val isLoading = false
    LoadingOverlay(
        isLoading = isLoading,
        message = stringResource(R.string.loading_message)
    ) {
        GlassBackground(backgroundRes = R.drawable.bg) { backdrop ->
            GlassCard(
                backdrop = backdrop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .windowInsetsPadding(WindowInsets.safeDrawing)
                    .padding(horizontal = 18.dp)
                    .fillMaxWidth()
                    .height(420.dp)
                    .safeContentPadding(),
                cornerRadius = 40.dp,
                blurRadius = 45.dp,
                refractionHeight = 12.dp,
                refractionAmount = 12.dp,
                depthEffect = true,
                verticalArrangement = Arrangement.Center
            ) {
                // Super Admin / Log in / fields / button
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Super Admin", color = GlassTextPrimary)
                    Text("Sign up", color = GlassTextPrimary)
                }

                GlassPill(backdrop) {

                }

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
}