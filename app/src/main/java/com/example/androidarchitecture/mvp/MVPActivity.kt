package com.example.androidarchitecture.mvp

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
import com.example.androidarchitecture.R

class MVPActivity : ComponentActivity() , com.example.androidarchitecture.mvp.View {
    lateinit var listValues: ArrayList<String>
    lateinit var arrayAdapter: ArrayAdapter<String>
    lateinit var listView: ListView
    lateinit var presenter: CountriesPresenter
    lateinit var retryButton: Button
    lateinit var progess: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)
        setTitle("MVPActivity")

        presenter = CountriesPresenter(this)
        listView = findViewById(R.id.list)
        retryButton = findViewById(R.id.retryButton)
        progess = findViewById(R.id.progress)
        listValues = arrayListOf()
        arrayAdapter = ArrayAdapter(this,R.layout.row_layout,
            R.id.listText,listValues )
        listView.adapter = arrayAdapter
        listView.setOnItemClickListener(object: AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@MVPActivity,"you clicked ${listView.getItemAtPosition(p2)}",
                    Toast.LENGTH_LONG).show()
            }
        })

    }

    override fun setValues(values:List<String>){
        listValues.clear()
        listValues.addAll(values)
        retryButton.visibility = View.GONE
        progess.visibility = View.GONE
        arrayAdapter.notifyDataSetChanged()
    }

    fun onRetry(view : View ){
        presenter.onRefresh()
        listView.visibility = View.GONE
        retryButton.visibility = View.GONE
        progess.visibility = View.VISIBLE
    }
    override fun onError(){
        listView.visibility = View.GONE
        retryButton.visibility = View.VISIBLE
        progess.visibility = View.GONE
    }


    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MVPActivity::class.java)
        }
    }
}