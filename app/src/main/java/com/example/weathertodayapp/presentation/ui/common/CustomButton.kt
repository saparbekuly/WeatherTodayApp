package com.example.weathertodayapp.presentation.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weathertodayapp.ui.theme.backgroundBtnNotEnabled
import com.example.weathertodayapp.ui.theme.black
import com.example.weathertodayapp.ui.theme.yellowButton

@Composable
fun CustomYellowButton(
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = yellowButton,
            contentColor = black,
            disabledContainerColor = backgroundBtnNotEnabled
        ),
        shape = RoundedCornerShape(12.dp),
    ) {
        content()
    }
}