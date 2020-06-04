package com.vinay.dependencywithkoinapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vinay.dependencywithkoinapp.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel (private val repo: UserRepository) : ViewModel(), Callback<List<User>> {

    private val _loadingState = MutableLiveData<UiState>()
    val loadingState: LiveData<UiState>
        get() = _loadingState

    private val _data = MutableLiveData<List<User>>()
    val data: MutableLiveData<List<User>>
        get() = _data

    init {
        fetchData()
    }

    private fun fetchData() {
        _loadingState.postValue(Loading)
        repo.getAllUsers().enqueue(this)
    }

    override fun onFailure(call: Call<List<User>>, t: Throwable) {
        _loadingState.postValue(Error(t))
    }

    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
        if (response.isSuccessful) {
            _data.postValue(response.body())
            _loadingState.postValue(Success)
        } else {

        }
    }
}