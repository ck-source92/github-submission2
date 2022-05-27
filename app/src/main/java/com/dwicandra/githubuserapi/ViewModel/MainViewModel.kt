package com.dwicandra.githubuserapi.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dwicandra.githubuserapi.API.ApiConfig
import com.dwicandra.githubuserapi.Model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _listUserGithub = MutableLiveData<ArrayList<ItemsItem>>()
    val listUserGithub: LiveData<ArrayList<ItemsItem>> = _listUserGithub

    private val _listFollowersGithub = MutableLiveData<ArrayList<ItemsItem>>()
    val listFollowersGithub: LiveData<ArrayList<ItemsItem>> = _listFollowersGithub

    private val _listFollowingGithub = MutableLiveData<ArrayList<ItemsItem>>()
    val listFollowingGithub: LiveData<ArrayList<ItemsItem>> = _listFollowingGithub

    private val _detailUserGithub = MutableLiveData<ResponseDetailUser>()
    val detailUserGithub: LiveData<ResponseDetailUser> = _detailUserGithub

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getSearchUsers(query: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getSearchUsers(query)
        client.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listUserGithub.postValue(response.body()?.items)
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                _isLoading.value = false
                Log.d("onFailure", t.message.toString())
            }
        })
    }

    fun getDetailUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUsers(username)
        client.enqueue(object : Callback<ResponseDetailUser> {
            override fun onResponse(
                call: Call<ResponseDetailUser>,
                response: Response<ResponseDetailUser>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detailUserGithub.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ResponseDetailUser>, t: Throwable) {
                _isLoading.value = false
                Log.d("onFailure", t.message.toString())
            }
        })
    }

    fun getFollowersUsers(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowersUsers(username)
        client.enqueue(object : Callback<ArrayList<ItemsItem>> {
            override fun onResponse(
                call: Call<ArrayList<ItemsItem>>,
                response: Response<ArrayList<ItemsItem>>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _listFollowersGithub.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ArrayList<ItemsItem>>, t: Throwable) {
                _isLoading.value = false
                Log.d("onFailure", t.message.toString())
            }
        })
    }

    fun getFollowingUsers(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowingUsers(username)
        client.enqueue(object : Callback<ArrayList<ItemsItem>> {
            override fun onResponse(
                call: Call<ArrayList<ItemsItem>>,
                response: Response<ArrayList<ItemsItem>>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _listFollowingGithub.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ArrayList<ItemsItem>>, t: Throwable) {
                _isLoading.value = false
                Log.d("onFailure", t.message.toString())
            }
        })
    }


}