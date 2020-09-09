package com.example.rxjava

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import java.util.function.Predicate

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Observables out of a list of objects
        val taskObservable: Observable<Task> = Observable
            .fromIterable(DataSource.createTasksList())
            .subscribeOn(Schedulers.io())
            .filter { task ->
                task.isComplete
            }
            .observeOn(AndroidSchedulers.mainThread())

        taskObservable.subscribe(object : Observer<Task> {
            override fun onComplete() {
                Log.d(TAG, "onComplete: called.")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: called")
                disposable.add(d)
            }

            override fun onNext(task: Task) {
                Log.d(TAG, "onNext: " + Thread.currentThread().name)
                Log.d(TAG, "onNext: " + task.description)
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: ", e)
            }

        })

        // Creating observable out of a single object (Create Operator)
        val task: Task = Task("Walk the dog", false, 3)
        val taskObservable2: Observable<Task> = Observable
            .create(ObservableOnSubscribe<Task> { emitter ->
                if (!emitter.isDisposed) {
                    emitter.onNext(task)
                    emitter.onComplete()
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        taskObservable2.subscribe(object : Observer<Task> {
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

        }
        )

        // Creating observable out of a list of objects (Create Operator)
        val taskObservable3: Observable<Task> = Observable
            .create(ObservableOnSubscribe<Task> { emitter ->
                DataSource.createTasksList().forEach { task ->
                    if (!emitter.isDisposed) {
                        emitter.onNext(task)
                    }
                }

                if (!emitter.isDisposed) {
                    emitter.onComplete()
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        taskObservable3.subscribe(object : Observer<Task> {
            override fun onComplete() {
                //
            }

            override fun onSubscribe(d: Disposable) {
                //
            }

            override fun onNext(t: Task) {
                Log.d(TAG, "onNext3: ${t.description}")
            }

            override fun onError(e: Throwable) {
                //
            }

        }
        )

        // Range operator
        val observable: Observable<Int> = Observable.range(0, 9)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        observable.subscribe(object : Observer<Int>{
            override fun onComplete() {
                //
            }

            override fun onSubscribe(d: Disposable) {
                //
            }

            override fun onNext(t: Int) {
                Log.d(TAG, "onNext4: $t")
            }

            override fun onError(e: Throwable) {
                //
            }

        })

        // interval operator
        val intervalObservable: Observable<Long> = Observable.interval(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .takeWhile { long ->
                return@takeWhile long <= 5
            }
            .observeOn(AndroidSchedulers.mainThread())

        intervalObservable.subscribe(object : Observer<Long>{
            override fun onComplete() {
                //
            }

            override fun onSubscribe(d: Disposable) {
                //
            }

            override fun onNext(t: Long) {
                Log.d(TAG, "onNext5: $t")
            }

            override fun onError(e: Throwable) {
                //
            }

        })

        val timerObservable = Observable
            .timer(3, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        timerObservable.subscribe(object: Observer<Long>{
            override fun onComplete() {
                //
            }

            override fun onSubscribe(d: Disposable) {
                //
            }

            override fun onNext(t: Long) {
                Log.d(TAG, "onNext6: $t")
            }

            override fun onError(e: Throwable) {
                //
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}