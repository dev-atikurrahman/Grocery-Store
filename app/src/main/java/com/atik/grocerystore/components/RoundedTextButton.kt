package com.atik.grocerystore.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atik.grocerystore.ui.theme.GlassLightStrong
import com.atik.grocerystore.ui.theme.GlassTextPrimary

@Composable
fun RoundedTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    cornerRadius: Dp = 12.dp,
    shape: Shape = RoundedCornerShape(cornerRadius),
    backgroundColor: Color = GlassLightStrong.copy(alpha = 0.85f),
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.textButtonColors(
            containerColor = backgroundColor
        ),
        contentPadding = contentPadding
    ) {
        AppText(
            text = text,
            color = GlassTextPrimary,
            style = TextStyle(
                fontSize = 16.sp
            )
        )
    }
}
