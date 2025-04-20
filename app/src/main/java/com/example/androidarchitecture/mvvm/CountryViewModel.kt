package com.example.androidarchitecture.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidarchitecture.model.CountriesService
import com.example.androidarchitecture.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CountryViewModel : ViewModel() {
    lateinit var countriesNameLiveData: MutableLiveData<List<String>>
    lateinit var errorLiveData: MutableLiveData<Boolean>
    lateinit var service: CountriesService

    init {
        service = CountriesService()
        countriesNameLiveData = MutableLiveData()
        errorLiveData = MutableLiveData()
        fetchCountries()
    }
    fun getCountriesNameLiveData():LiveData<List<String>>{
        return countriesNameLiveData
    }
    fun getErrorLiveData() : LiveData<Boolean> {
        return errorLiveData
    }
    fun fetchCountries() {

        val disposable = service.getCountries()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(
                object : DisposableSingleObserver<List<Country>>() {

                    override fun onError(e: Throwable) {
                        Log.i("onError", e.toString())
                        e.printStackTrace()
                        errorLiveData.value = true
                    }

                    override fun onSuccess(t: List<Country>) {
                        val list: MutableList<String> = mutableListOf()
                        for (country in t) {
                            list.add(country.countryName)
                        }

                        countriesNameLiveData.value = list
                        errorLiveData.value = false
                    }
                }
            )
    }

    fun onRefresh() {
        fetchCountries()
    }
}