package com.demo.logintask.network

import com.demo.logintask.model.LoginModel
import com.demo.logintask.model.RegisterModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response


object MainRepo {

    suspend fun registerUser(
        name: String,
        email: String,
        mobile: String,
        password: String,
        password_confirmation: String,
    ): Response<RegisterModel>  {
        return RetrofitInstance.authService.registerUser(name,email,mobile,password,password_confirmation)
    }

    suspend fun loginUser(
        email: String,
        password: String,
    ): Response<LoginModel>  {
        return RetrofitInstance.authService.loginUser(email,password)
    }

}