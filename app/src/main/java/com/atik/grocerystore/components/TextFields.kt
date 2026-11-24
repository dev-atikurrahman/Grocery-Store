package com.atik.grocerystore.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.atik.grocerystore.ui.theme.BrandPrimary
import com.atik.grocerystore.ui.theme.ErrorTextRed
import com.atik.grocerystore.ui.theme.GlassTextFieldContainer
import com.atik.grocerystore.ui.theme.GlassTextFieldContainerUnFocus
import com.atik.grocerystore.ui.theme.GlassTextHint
import com.atik.grocerystore.ui.theme.GlassTextPrimary

val roundedShape = RoundedCornerShape(12.dp)

enum class EndIconMode { NONE, CLEAR_TEXT, PASSWORD_TOGGLE }

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    trailingActionText: String? = null,
    onTrailingActionClick: (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = roundedShape,
    endIconMode: EndIconMode = EndIconMode.NONE,
) {

    var passwordVisibility by remember { mutableStateOf(false) }
    val isPasswordField = endIconMode == EndIconMode.PASSWORD_TOGGLE

    val effectiveKeyboardOptions = if (isPasswordField) {
        keyboardOptions.copy(keyboardType = KeyboardType.Password)
    } else {
        keyboardOptions
    }

    val visualTransformation = if (endIconMode == EndIconMode.PASSWORD_TOGGLE && !passwordVisibility) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    val isNotEmpty = value.isNotEmpty()

    val containerColor = MaterialTheme.colorScheme.background
    val iconBadgeColor = MaterialTheme.colorScheme.background
    val contentColor = MaterialTheme.colorScheme.onBackground



    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { HintText(text = label)},
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {

        },
        keyboardOptions = effectiveKeyboardOptions,
        singleLine = true,
        shape = shape,
        visualTransformation = visualTransformation,
        trailingIcon = {},
        colors = TextFieldColors(
            focusedTextColor = GlassTextPrimary,
            unfocusedTextColor = GlassTextHint,
            errorTextColor = ErrorTextRed,
            focusedContainerColor = GlassTextFieldContainer,
            unfocusedContainerColor = GlassTextFieldContainerUnFocus,
            cursorColor = GlassTextHint,
            errorCursorColor = ErrorTextRed,
            errorIndicatorColor = ErrorTextRed,
            focusedLeadingIconColor = GlassTextPrimary,
            unfocusedLeadingIconColor = GlassTextPrimary,
            errorLeadingIconColor = ErrorTextRed,
            focusedTrailingIconColor = BrandPrimary,
            unfocusedTrailingIconColor = GlassTextPrimary,
            errorTrailingIconColor = ErrorTextRed,
            focusedLabelColor = GlassTextPrimary,
            unfocusedLabelColor = GlassTextHint,
            errorLabelColor = ErrorTextRed,
            focusedPlaceholderColor = GlassTextHint,
            unfocusedPlaceholderColor = GlassTextHint,
            errorPlaceholderColor = ErrorTextRed,
        )
    )

}