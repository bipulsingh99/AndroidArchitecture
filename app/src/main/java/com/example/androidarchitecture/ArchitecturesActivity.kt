package com.example.androidarchitecture

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.example.androidarchitecture.mvc.MVCActivity
import com.example.androidarchitecture.mvp.MVPActivity
import com.example.androidarchitecture.mvvm.MVVMActivity

class ArchitecturesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_architectures)

    }
    fun onMVC(view:View){
        startActivity(MVCActivity.getIntent(this))
    }
    fun onMVP(view:View){
        startActivity(MVPActivity.getIntent(this))
    }
    fun onMVVM(view: View){
        startActivity(MVVMActivity.getIntent(this))
    }
}
