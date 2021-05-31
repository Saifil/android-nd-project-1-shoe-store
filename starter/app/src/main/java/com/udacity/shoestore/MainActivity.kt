package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        // hook up this base view model which will handle the global states
        // TODO: move login state management to MainViewModel
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
}
