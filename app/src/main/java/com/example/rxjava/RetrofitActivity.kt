package com.example.rxjava

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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