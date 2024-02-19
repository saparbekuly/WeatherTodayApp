package com.example.weathertodayapp.utils.extension

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import com.example.weathertodayapp.R
import com.example.weathertodayapp.data.model.ErrorResponse
import com.example.weathertodayapp.domain.model.location.CityItem
import com.example.weathertodayapp.domain.network.Response
import com.example.weathertodayapp.utils.UiText
import com.google.gson.Gson
import retrofit2.HttpException


@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

fun <T> Throwable.catchError(): Response<T> {
    this.printStackTrace()
    return when (this) {
        is HttpException -> {
            try {
                val errorResponse = Gson().fromJson(
                    this.response()?.errorBody()?.string(),
                    ErrorResponse::class.java
                )
                if (errorResponse.code == 86)
                    Response.Error(UiText.DynamicString(errorResponse.code.toString()))
                else Response.Error(UiText.DynamicString(errorResponse.message))
            } catch (e: java.lang.Exception) {
                Response.Error(UiText.StringResource(R.string.something_went_wrong))
            }
        }

        is IllegalStateException -> {
            Response.Error(UiText.StringResource(R.string.something_went_wrong))
        }

        else -> {
            Response.NetworkError
        }
    }
}

fun Modifier.shimmerLoadingAnimation(
    widthOfShadowBrush: Int = 500,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 1000,
): Modifier {
    return composed {

        val shimmerColors = listOf(
            Color.White.copy(alpha = 0.3f),
            Color.White.copy(alpha = 0.5f),
            Color.White.copy(alpha = 1.0f),
            Color.White.copy(alpha = 0.5f),
            Color.White.copy(alpha = 0.3f),
        )

        val transition = rememberInfiniteTransition(label = "")

        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = durationMillis,
                    easing = LinearEasing,
                ),
                repeatMode = RepeatMode.Restart,
            ),
            label = "Shimmer loading animation",
        )

        this.background(
            brush = Brush.linearGradient(
                colors = shimmerColors,
                start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
                end = Offset(x = translateAnimation.value, y = angleOfAxisY),
            ),
        )
    }
}

class AssetParamType : NavType<CityItem>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): CityItem? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): CityItem {
        return Gson().fromJson(value, CityItem::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: CityItem) {
        bundle.putParcelable(key, value)
    }
}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}