package com.example.rxjava.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rxjava.data.Repository
import okhttp3.ResponseBody

class RetrofitViewModel : ViewModel() {
    private val repository: Repository = Repository.getInstance()

    fun makeQuery(): LiveData<ResponseBody> {
        return repository.makeReactiveQuery()
    }
}