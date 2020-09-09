package com.example.rxjava

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import okhttp3.ResponseBody

class RetrofitViewModel : ViewModel() {
    private val repository: Repository

    init {
        repository = Repository.getInstance()
    }

    fun makeQuery(): LiveData<ResponseBody> {
        return repository.makeReactiveQuery()
    }
}