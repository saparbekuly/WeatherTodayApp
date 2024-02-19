package com.example.weathertodayapp.presentation.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.weathertodayapp.R
import com.example.weathertodayapp.ui.theme.Dimens
import com.example.weathertodayapp.ui.theme.Paddings

@Composable
fun Empty(
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))
        Image(
            painterResource(R.drawable.ic_empty),
            contentDescription = null,
            modifier = Modifier.size(Dimens.emptyIconSize)
        )
        Spacer(Modifier.height(Paddings.normal))
        Text(
            text = text,
            modifier = Modifier,
        )
        Spacer(Modifier.weight(2f))
    }
}