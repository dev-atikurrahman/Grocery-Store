package com.atik.grocerystore.feature.auth.presentation.login

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AlternateEmail
import androidx.compose.material.icons.rounded.Key
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atik.grocerystore.R
import com.atik.grocerystore.VerticalSpacer
import com.atik.grocerystore.components.AppPasswordField
import com.atik.grocerystore.components.AppText
import com.atik.grocerystore.components.AppTextField
import com.atik.grocerystore.components.AuthButton
import com.atik.grocerystore.components.AuthHeadingText
import com.atik.grocerystore.components.GlassBackground
import com.atik.grocerystore.components.GlassCard
import com.atik.grocerystore.components.LoadingOverlay
import com.atik.grocerystore.ui.theme.ErrorTextRed
import com.atik.grocerystore.ui.theme.GlassTextPrimary
import com.atik.grocerystore.ui.theme.GroceryStoreTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    val submit: () -> Unit = {
        focusManager.clearFocus()
        viewModel.onEvent(LoginEvent.SubmitClicked)
    }

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                LoginEffect.LoginSuccess -> onLoginSuccess()
            }
        }
    }

    LoginScreenContent(
        uiState = uiState,
        emailState = viewModel.emailState,
        passwordState = viewModel.passwordState,
        onSubmit = submit
    )
}

@Composable
fun LoginScreenContent(
    uiState: LoginUiState,
    emailState: TextFieldState,
    passwordState: TextFieldState,
    onSubmit: () -> Unit
) {
    LoadingOverlay(
        isLoading = uiState.isLoading,
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
                    .animateContentSize(),
                cornerRadius = 40.dp,
                blurRadius = 45.dp,
                refractionHeight = 12.dp,
                refractionAmount = 12.dp,
                depthEffect = true,
                verticalArrangement = Arrangement.Center
            ) { cardBackdrop ->
                VerticalSpacer(size = 18.dp)
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Super Admin", color = GlassTextPrimary)
                    AuthHeadingText("Sign up", color = GlassTextPrimary)
                }
                VerticalSpacer(size = 56.dp)

                AppText("Log In", color = GlassTextPrimary, fontSize = 32.sp)
                VerticalSpacer(size = 18.dp)

                AppTextField(
                    state = emailState,
                    placeholder = stringResource(R.string.e_mail_address),
                    backdrop = cardBackdrop,
                    leadingIcon = Icons.Rounded.AlternateEmail,
                    errorText = uiState.emailError
                )

                VerticalSpacer(size = 16.dp)

                AppPasswordField(
                    state = passwordState,
                    placeholder = stringResource(R.string.password),
                    backdrop = cardBackdrop,
                    leadingIcon = Icons.Rounded.Key,
                    showIcon = Icons.Rounded.Visibility,
                    hideIcon = Icons.Rounded.VisibilityOff,
                    errorText = uiState.passwordError,
                    onImeAction = onSubmit
                )

                uiState.generalError?.let { error ->
                    VerticalSpacer(size = 12.dp)
                    Text(text = error, color = ErrorTextRed)
                }

                VerticalSpacer(size = 24.dp)

                AppText(
                    text = stringResource(R.string.disclaimer_text),
                    color = GlassTextPrimary,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Justify,
                    lineHeight = 16.sp,
                    letterSpacing = 0.5.sp
                )

                AuthButton(
                    text = "Log in",
                    onClick = onSubmit,
                    enabled = !uiState.isLoading
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    GroceryStoreTheme {
        LoginScreenContent(
            uiState = LoginUiState(),
            emailState = rememberTextFieldState(),
            passwordState = rememberTextFieldState(),
            onSubmit = {}
        )
    }
}
