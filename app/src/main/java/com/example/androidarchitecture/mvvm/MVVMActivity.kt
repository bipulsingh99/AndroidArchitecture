package com.example.androidarchitecture.mvvm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.androidarchitecture.R
import com.example.androidarchitecture.mvp.CountriesPresenter

class MVVMActivity : ComponentActivity() {

    lateinit var listValues: ArrayList<String>
    lateinit var arrayAdapter: ArrayAdapter<String>
    lateinit var listView: ListView
    lateinit var viewModel: CountryViewModel
    lateinit var retryButton: Button
    lateinit var progess: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)
        setTitle("MVVCActivity")

        viewModel = CountryViewModel()
        listView = findViewById(R.id.list)
        retryButton = findViewById(R.id.retryButton)
        progess = findViewById(R.id.progress)
        listValues = arrayListOf()
        arrayAdapter = ArrayAdapter(this,R.layout.row_layout,
            R.id.listText,listValues )
        listView.adapter = arrayAdapter
        listView.setOnItemClickListener(object: AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@MVVMActivity,"you clicked ${listView.getItemAtPosition(p2)}",
                    Toast.LENGTH_LONG).show()
            }
        })
        observeViewModel()
    }
    private fun observeViewModel(){
        viewModel.getCountriesNameLiveData().observe(this as LifecycleOwner, Observer {
            countryList ->
            if(countryList!=null)
             setValues(countryList)
        })
    }

    fun setValues(values:List<String>){
        listValues.clear()
        listValues.addAll(values)
        retryButton.visibility = View.GONE
        progess.visibility = View.GONE
        arrayAdapter.notifyDataSetChanged()
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MVVMActivity::class.java)
        }
    }
}