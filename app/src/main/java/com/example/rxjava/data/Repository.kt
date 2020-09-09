package com.example.rxjava.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.example.rxjava.data.network.ServiceGenerator
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody


class Repository {


    fun makeReactiveQuery(): LiveData<ResponseBody> {
        return LiveDataReactiveStreams.fromPublisher(
            ServiceGenerator.getRequestApi().makeQuery().subscribeOn(Schedulers.io())
        )
    }

    companion object {

        private var instance: Repository? = null

        fun getInstance(): Repository {
            if (instance == null) {
                instance =
                    Repository()
            }
            return instance!!
        }

    }
}