package com.atik.grocerystore.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atik.grocerystore.ui.theme.GlassLightStrong
import com.atik.grocerystore.ui.theme.GlassTextPrimary
import com.atik.grocerystore.ui.theme.OnPrimaryLight
import com.kyant.backdrop.Backdrop


@Composable
fun RoundedCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    backdrop: Backdrop? = null
) {
    val interactionSource = remember { MutableInteractionSource() }

    val backgroundColor by animateColorAsState(
        targetValue = if (checked) {
            GlassLightStrong.copy(alpha = 0.85f)
        } else {
            OnPrimaryLight.copy(alpha = 0.08f)
        },
        label = "checkboxBackground"
    )

    val borderColor by animateColorAsState(
        targetValue = if (checked) {
            GlassLightStrong
        } else {
            OnPrimaryLight.copy(alpha = 0.45f)
        },
        label = "checkboxBorder"
    )

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(RoundedCornerShape(7.dp))
                .background(backgroundColor)
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(7.dp)
                )
                .clickable(
                    enabled = enabled,
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onCheckedChange(!checked)
                },
            contentAlignment = Alignment.Center
        ) {
            this@Row.AnimatedVisibility(visible = checked) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = null,
                    tint = GlassTextPrimary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        label?.let {
            Text(
                text = it,
                color = GlassTextPrimary,
                fontSize = 13.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable(enabled = enabled) {
                        onCheckedChange(!checked)
                    }
                    .padding(horizontal = 2.dp)

            )
        }
    }
}
