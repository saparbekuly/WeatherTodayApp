package com.example.weathertodayapp.presentation.ui.locations

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.weathertodayapp.R
import com.example.weathertodayapp.domain.model.location.CityItem
import com.example.weathertodayapp.presentation.state.ComponentRectangleLineLong
import com.example.weathertodayapp.presentation.state.ManagementResourceUiState
import com.example.weathertodayapp.presentation.ui.common.CustomTopAppBar
import com.example.weathertodayapp.presentation.ui.common.CustomYellowButton
import com.example.weathertodayapp.ui.theme.Paddings
import com.example.weathertodayapp.utils.Screen
import com.example.weathertodayapp.utils.extension.noRippleClickable
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

@Composable
fun LocationScreen(
    navController: NavHostController,
    locationViewModel: LocationViewModel = koinViewModel()
) {
    val locationUiState by locationViewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        locationViewModel.setEvent(LocationContract.Event.OnInit)
    }
    LocationContent(
        state = locationUiState,
        onClickSearch = {
            navController.navigate(Screen.Search.route)
        },
        onRemoveItem = {
            locationViewModel.setEvent(LocationContract.Event.OnLocationDeleted(it.id))
        },
        onClickItem = {
            val json = Uri.encode(Gson().toJson(it))
            navController.navigate("${Screen.WeatherDetail.route}/$json")
        }
    )
}

@Composable
private fun LocationContent(
    state: LocationContract.State,
    onClickSearch: () -> Unit,
    onClickItem: (CityItem) -> Unit,
    onRemoveItem: (CityItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.run {
            fillMaxSize()
        }
    ) {
        Box {
            Box(
                Modifier.zIndex(1f)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(
                            start = Paddings.medium,
                            end = Paddings.medium,
                            bottom = Paddings.xxLarge
                        )
                ) {
                    CustomTopAppBar(
                        title = stringResource(R.string.locations)
                    )
                    ManagementResourceUiState(
                        resourceUiState = state.locationListState,
                        successView = {
                            LazyColumn {
                                items(it) { cityItem ->
                                    LocationItem(
                                        cityItem = cityItem,
                                        onClick = {
                                            onClickItem(cityItem)
                                        },
                                        onRemove = {
                                            onRemoveItem(cityItem)
                                        }
                                    )
                                }
                            }
                        },
                        loadingView = {
                            Column {
                                for (i in 1..5) {
                                    LocationItemOnLoading()
                                }
                            }
                        },
                        onCheckAgain = {},
                        onTryAgain = {},
                    )
                }
            }
            Box(
                modifier.zIndex(2f)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = Paddings.medium)
                        .align(Alignment.BottomCenter)
                ) {
                    Spacer(
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    CustomYellowButton(onClick = onClickSearch, content = {
                        Text(
                            text = stringResource(R.string.add_location),
                        )
                    })
                }
            }
        }

    }
}

@Composable
fun LocationItem(
    modifier: Modifier = Modifier,
    cityItem: CityItem,
    onClick: (CityItem) -> Unit,
    onRemove: (CityItem) -> Unit
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = cityItem.name,
                        maxLines = 1,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = cityItem.state,
                        maxLines = 1,
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_basket),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(Paddings.medium)
                        .noRippleClickable {
                            onRemove(cityItem)
                        }
                )
            }
        }
    }
}

@Composable
fun LocationItemOnLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(top = Paddings.xxSmall, bottom = Paddings.small6dp)
            .shadow(Paddings.extraSmall, shape = RoundedCornerShape(Paddings.small))
            .background(Color.White, RoundedCornerShape(Paddings.small))
            .fillMaxWidth()
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