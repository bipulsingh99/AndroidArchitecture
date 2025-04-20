package com.example.androidarchitecture.model

import com.google.gson.annotations.SerializedName

class Country constructor(
    @SerializedName("name") val countryName: String
) {

}