package com.demo.logintask.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.logintask.model.LoginModel
import com.demo.logintask.model.RegisterModel
import com.demo.logintask.network.MainRepo
import com.demo.logintask.utils.Constants
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class MainViewModel : ViewModel() {

    private var registerDataPrivate = MutableLiveData<RegisterModel>()
    var registerData : LiveData<RegisterModel> = registerDataPrivate

    private var loginDataPrivate = MutableLiveData<LoginModel>()
    var loginData : LiveData<LoginModel> = loginDataPrivate

    private var loginCompletePrivate = MutableLiveData<Boolean?>()
    var loginComplete : LiveData<Boolean?> = loginCompletePrivate

    var repo = MainRepo

    fun registerUser(
        name: String,
        email: String,
        mobile: String,
        password: String,
        passwordConfirmation: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repo.registerUser(name, email, mobile, password, passwordConfirmation)
                if(response.isSuccessful && response.code() == 200){
                    registerDataPrivate.postValue(response.body())
                    loginCompletePrivate.postValue(true)
                }else{
                    val error = response.errorBody()?.string().toString()
                    val res = Gson().fromJson(error,RegisterModel::class.java)
                    registerDataPrivate.postValue(res)
                }
            } catch (e: Exception) {

            }
        }
    }

    fun login(
        email: String,
        password: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repo.loginUser(email, password)
                if(response.isSuccessful && response.code() == 200){
                    loginDataPrivate.postValue(response.body())
                    loginCompletePrivate.postValue(true)
                }else{
                    val error = response.errorBody()?.string().toString()
                    val res = Gson().fromJson(error,LoginModel::class.java)
                    loginDataPrivate.postValue(res)
                }
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }
    }
}