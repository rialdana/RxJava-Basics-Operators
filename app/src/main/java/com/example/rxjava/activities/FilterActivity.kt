package com.example.rxjava.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjava.R
import com.example.rxjava.data.DataSource
import com.example.rxjava.models.Task
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers

class FilterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        // Filter operator
        val taskObservable = Observable.fromIterable(DataSource.createTasksList())
            .filter(Predicate<Task> { task ->
                return@Predicate task.description == "Walk the dog"
            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        taskObservable.subscribe(object : Observer<Task> {
            override fun onComplete() {
                //
            }

            override fun onSubscribe(d: Disposable) {
                //
            }

            override fun onNext(t: Task) {
                Log.d(TAG, "onNext: ${t.description}")
            }

            override fun onError(e: Throwable) {
                //
            }

        })

        // Take and take while operators
        val task2Observable = Observable.fromIterable(DataSource.createTasksList())
            .take(3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        task2Observable.subscribe(object : Observer<Task> {
            override fun onComplete() {
                //
            }

            override fun onSubscribe(d: Disposable) {
                //
            }

            override fun onNext(t: Task) {
                Log.d(TAG, "onNext2: ${t.description}")
            }

            override fun onError(e: Throwable) {
                //
            }

        })

    }

    companion object {
        const val TAG = "FilterActivity"
    }
}