package com.example.weathertodayapp.presentation.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.weathertodayapp.R
import com.example.weathertodayapp.domain.model.location.CityItem
import com.example.weathertodayapp.presentation.state.ComponentRectangleLineLong
import com.example.weathertodayapp.presentation.state.ManagementResourceUiState
import com.example.weathertodayapp.presentation.ui.common.CityTextField
import com.example.weathertodayapp.presentation.ui.common.CustomTopAppBar
import com.example.weathertodayapp.presentation.ui.common.CustomYellowButton
import com.example.weathertodayapp.ui.theme.Paddings
import com.example.weathertodayapp.utils.extension.noRippleClickable
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = koinViewModel()
) {
    val searchUiState by searchViewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        searchViewModel.effect.collect { effect ->
            when (effect) {
                SearchContract.Effect.OnAddedSuccessfully -> {
                    navController.popBackStack()
                }
            }
        }
    }
    Column(
        modifier = Modifier.run {
            fillMaxSize().padding(horizontal = Paddings.medium)
        }
    ) {
        CustomTopAppBar(
            title = stringResource(R.string.search_location),
            navigationIcon = painterResource(id = R.drawable.ic_back),
            onNavigationClick = {
                navController.popBackStack()
            }
        )
        Spacer(modifier = Modifier.height(Paddings.medium))
        CityTextField(
            textState = searchUiState.locationFieldState,
            placeholder = stringResource(R.string.enter_city_field)
        )
        CustomYellowButton(onClick = {
            searchViewModel.setEvent(
                SearchContract.Event.OnSearchClicked
            )
        }, enabled = searchUiState.isButtonEnabled.value, content = {
            Text(text = stringResource(R.string.search))
        })
        ManagementResourceUiState(
            modifier = Modifier.padding(bottom = Paddings.medium),
            resourceUiState = searchUiState.geoLocationState,
            successView = {
                Column {
                    it.forEach { it ->
                        CityItemView(
                            cityItem = it,
                            onClick = {
                                searchViewModel.setEvent(
                                    SearchContract.Event.OnAddLocation(it)
                                )
                            }
                        )
                    }
                }
            },
            loadingView = {
                Column {
                    for (i in 1..5) {
                        CityItemViewOnLoading()
                    }
                }
            },
            onCheckAgain = {},
            onTryAgain = {},
        )
    }
}

@Composable
fun CityItemView(
    modifier: Modifier = Modifier,
    cityItem: CityItem,
    onClick: (CityItem) -> Unit
) {
    Box(
        modifier = modifier
            .padding(top = Paddings.xxSmall, bottom = Paddings.small6dp)
            .shadow(Paddings.extraSmall, shape = RoundedCornerShape(Paddings.small))
            .background(Color.White, RoundedCornerShape(Paddings.small))
            .fillMaxWidth()
            .wrapContentHeight()
            .noRippleClickable { onClick(cityItem) }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(Paddings.medium),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = cityItem.name,
                maxLines = 1,
                textAlign = TextAlign.Start
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = cityItem.state,
                    maxLines = 1,
                )
                Text(
                    text = cityItem.country,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
private fun CityItemViewOnLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(top = Paddings.xxSmall, bottom = Paddings.small6dp)
            .shadow(Paddings.extraSmall, shape = RoundedCornerShape(Paddings.small))
            .background(Color.White, RoundedCornerShape(Paddings.small))
            .fillMaxWidth()
            .wrapContentHeight()
            .wrapContentHeight()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(Paddings.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ComponentRectangleLineLong()
        }
    }
}