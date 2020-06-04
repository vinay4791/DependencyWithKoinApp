package com.vinay.dependencywithkoinapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.vinay.dependencywithkoinapp.R
import com.vinay.dependencywithkoinapp.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val userViewModel by viewModel<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel.data.observe(this, Observer {
            // Populate the UI
            it?.let {
                Log.d("Koin Repsonse", it.toString())
            }
        })

        userViewModel.loadingState.observe(this, Observer {
            it.let {
                Log.d("Koin State", it.toString())
            }
        })
    }
}