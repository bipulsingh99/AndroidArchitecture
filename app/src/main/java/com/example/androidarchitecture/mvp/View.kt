package com.example.androidarchitecture.mvp

interface View {
    fun setValues(countryName:List<String>)
    fun onError()
}