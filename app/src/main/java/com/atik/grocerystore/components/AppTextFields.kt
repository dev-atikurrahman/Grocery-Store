package com.atik.grocerystore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.clearText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atik.grocerystore.ui.theme.ErrorTextRed
import com.atik.grocerystore.ui.theme.FieldHighlight
import com.atik.grocerystore.ui.theme.GlassBackdropFallback
import com.atik.grocerystore.ui.theme.GlassIconCircle
import com.atik.grocerystore.ui.theme.GlassLeadingIcon
import com.atik.grocerystore.ui.theme.GlassPillFill
import com.atik.grocerystore.ui.theme.GlassTextHint
import com.atik.grocerystore.ui.theme.GlassTextPrimary
import com.kyant.backdrop.Backdrop

private val FieldShape = RoundedCornerShape(50.dp)

/** Shared pieces*/


@Composable
private fun glassTextFieldColors(): TextFieldColors = TextFieldDefaults.colors(
    focusedTextColor = GlassTextPrimary,
    unfocusedTextColor = GlassTextPrimary,
    errorTextColor = ErrorTextRed,

    // Container transparent: glass pill ই আসল background দেবে
    focusedContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent,
    disabledContainerColor = Color.Transparent,
    errorContainerColor = Color.Transparent,

    // নিচের underline বন্ধ (pill design-এ লাগে না)
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    errorIndicatorColor = Color.Transparent,

    cursorColor = GlassTextPrimary,
    errorCursorColor = ErrorTextRed,

    focusedLeadingIconColor = GlassTextPrimary,
    unfocusedLeadingIconColor = GlassLeadingIcon,
    errorLeadingIconColor = ErrorTextRed,
    focusedTrailingIconColor = GlassTextPrimary,
    unfocusedTrailingIconColor = GlassLeadingIcon,
    errorTrailingIconColor = ErrorTextRed,

    focusedPlaceholderColor = GlassTextHint,
    unfocusedPlaceholderColor = GlassTextHint,
    errorPlaceholderColor = ErrorTextRed,
)

private val FieldTextStyle = TextStyle(fontFamily = PlushJakarta, fontSize = 16.sp, fontWeight = FontWeight.Medium)

/** Leading icon */
@Composable
private fun FieldLeadingIcon(icon: ImageVector, startPadding: Dp = 8.dp) {
    Box(
        modifier = Modifier
            .padding(start = startPadding)
            .size(40.dp)
            .clip(CircleShape)
            .background(GlassIconCircle),
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(20.dp))
    }
}

@Composable
private fun FieldContainer(
    backdrop: Backdrop?,
    errorText: String?,
    modifier: Modifier,
    field: @Composable () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (backdrop != null) {
            GlassCard(
                backdrop = backdrop,
                modifier = Modifier
                    .fillMaxWidth(),
                cornerRadius = 28.dp,
                blurRadius = 8.dp,
                refractionHeight = 8.dp,
                refractionAmount = 12.dp,
                tint = GlassPillFill,
                highlightWidth = 0.50.dp,
                highlightColor = FieldHighlight,
                highlightAngle = 45f,
                highlightFalloff = 1f,
                depthEffect = true,
                contentPadding = PaddingValues(0.dp)
            ) { field() }
        } else {
            field()
        }

        if (errorText != null) {
            Text(
                text = "* $errorText",
                color = ErrorTextRed,
                style = TextStyle(
                    fontSize = 12.sp
                ),
                modifier = Modifier.padding(start = 20.dp, top = 6.dp)
            )
        }
    }
}

@Composable
fun AppTextField(
    state: TextFieldState,
    placeholder: String,
    modifier: Modifier = Modifier,
    backdrop: Backdrop? = null,
    leadingIcon: ImageVector? = null,
    errorText: String? = null,
    keyboardType: KeyboardType = KeyboardType.Email,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {},
    enabled: Boolean = true,
    showClearButton: Boolean = true
) {

    FieldContainer(backdrop = backdrop, errorText, modifier) {
        TextField(
            state = state,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            textStyle = FieldTextStyle,
            lineLimits = TextFieldLineLimits.SingleLine,
            placeholder = { HintText(text = placeholder, color = GlassTextHint) },
            leadingIcon = leadingIcon?.let { icon -> { FieldLeadingIcon(icon) } },
            trailingIcon = if (showClearButton && enabled && state.text.isNotEmpty()) {
                {
                    IconButton(onClick = { state.clearText() }) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "Clear text"
                        )
                    }
                }
            } else null,
            contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(start = 20.dp),
            isError = errorText != null,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
                autoCorrectEnabled = false
            ),
            onKeyboardAction = KeyboardActionHandler { onImeAction() },
            shape = FieldShape,
            colors = glassTextFieldColors()
        )
    }

}

@Composable
fun AppPasswordField(
    state: TextFieldState,
    placeholder: String,
    showIcon: ImageVector,
    hideIcon: ImageVector,
    modifier: Modifier = Modifier,
    backdrop: Backdrop? = null,
    leadingIcon: ImageVector? = null,
    errorText: String? = null,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {},
    enabled: Boolean = true
) {
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    FieldContainer(backdrop = backdrop, errorText, modifier) {
        if (LocalInspectionMode.current) {
            TextField(
                state = state,
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                textStyle = FieldTextStyle,
                lineLimits = TextFieldLineLimits.SingleLine,
                placeholder = { HintText(text = placeholder, color = GlassTextHint) },
                leadingIcon = leadingIcon?.let { icon -> { FieldLeadingIcon(icon) } },
                trailingIcon = {
                    IconButton(onClick = { passwordHidden = !passwordHidden }) {
                        Icon(
                            imageVector = if (passwordHidden) showIcon else hideIcon,
                            contentDescription = if (passwordHidden) "Show password" else "Hide password"
                        )
                    }
                },
                contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(start = 20.dp),
                isError = errorText != null,
                shape = FieldShape,
                colors = glassTextFieldColors()
            )
        } else {
            SecureTextField(
                state = state,
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                textStyle = FieldTextStyle,
                placeholder = { HintText(text = placeholder, color = GlassTextHint) },
                leadingIcon = leadingIcon?.let { icon -> { FieldLeadingIcon(icon) } },
                trailingIcon = {
                    IconButton(onClick = { passwordHidden = !passwordHidden }) {
                        Icon(
                            imageVector = if (passwordHidden) showIcon else hideIcon,
                            contentDescription = if (passwordHidden) "Show password" else "Hide password"
                        )
                    }
                },
                contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(start = 20.dp),
                isError = errorText != null,
                textObfuscationMode = if (passwordHidden) {
                    TextObfuscationMode.RevealLastTyped
                } else {
                    TextObfuscationMode.Visible
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = if (passwordHidden) KeyboardType.Password else KeyboardType.PasswordVisible,
                    imeAction = imeAction,
                    autoCorrectEnabled = false
                ),
                onKeyboardAction = KeyboardActionHandler { onImeAction() },
                shape = FieldShape,
                colors = glassTextFieldColors()
            )
        }
    }
}

