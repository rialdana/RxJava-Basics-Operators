package com.example.rxjava.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rxjava.R
import com.example.rxjava.viewmodel.RetrofitViewModel
import java.io.IOException

class RetrofitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val retrofitViewModel = ViewModelProviders.of(this).get(RetrofitViewModel::class.java)

        retrofitViewModel.makeQuery().observe(this, Observer {
            it?.let { responseBody ->
                Log.d(TAG, "onChanged: this is a live data response!")
                try {
                    Log.d(TAG, "onChanged: " + responseBody.string())
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        })
    }

    companion object {
        private const val TAG = "RetrofitViewModel"
    }
}