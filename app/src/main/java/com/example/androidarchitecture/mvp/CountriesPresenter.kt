package com.example.androidarchitecture.mvp

import android.util.Log
import com.example.androidarchitecture.model.CountriesService
import com.example.androidarchitecture.model.Country
import com.example.androidarchitecture.mvc.MVCActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CountriesPresenter {
    lateinit var view : View
    lateinit var service: CountriesService;
    constructor(view: View){
        this.view = view
        service = CountriesService()
        fetchCountries()
    }
    fun fetchCountries(){

        val disposable = service.getCountries()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(
                object: DisposableSingleObserver<List<Country>>(){

                    override fun onError(e: Throwable) {
                        Log.i("onError",e.toString())
                        e.printStackTrace()
                        view.onError()
                    }

                    override fun onSuccess(t: List<Country>) {
                        val list:MutableList<String> = mutableListOf()
                        for(country in t){
                            list.add(country.countryName)
                        }

                        view.setValues(list)
                    }
                }
            )
    }
    fun onRefresh(){
        fetchCountries()
    }
}