package com.example.androidarchitecture.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers


interface CountriesApi {
    @GET("all")
    fun getCountries(): Single<List<Country>>
}