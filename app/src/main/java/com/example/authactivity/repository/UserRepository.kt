package com.example.authactivity.repository

import androidx.lifecycle.MutableLiveData
import com.example.authactivity.api.PartyApi
import com.example.authactivity.model.User
import com.example.authactivity.network.ResponseResult
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface UserRepository {
    fun fetchUser(): Observable<User>
}

class UserRepositoryImpl(private val api: PartyApi) : UserRepository {
    override fun fetchUser(): Observable<User> {
        return api.fetchUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}