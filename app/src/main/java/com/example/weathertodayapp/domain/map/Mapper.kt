package com.example.weathertodayapp.domain.map

import com.example.weathertodayapp.domain.network.Response


abstract class Mapper<FROM, TO> {

    abstract fun mapData(from: FROM): TO

    fun map(from: Response<FROM>): Response<TO> = mapSimpleResult(from)

    private fun mapSimpleResult(from: Response<FROM>): Response<TO> = when (from) {
        is Response.Error -> Response.Error(from.error)
        is Response.NetworkError -> Response.NetworkError
        is Response.Success -> Response.Success(mapData(from.data))
    }
}
