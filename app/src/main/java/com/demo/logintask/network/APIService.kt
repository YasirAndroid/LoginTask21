package com.demo.logintask.network

import com.demo.logintask.model.LoginModel
import com.demo.logintask.model.RegisterModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST
import okhttp3.ResponseBody
import retrofit2.Call

interface APIService {

    @Headers("Accept: application/json")
    @POST("/api/register")
    @FormUrlEncoded
    suspend fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("mobile") mobile: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirmation: String
    ): Response<RegisterModel>

    @Headers("Accept: application/json")
    @POST("/api/login")
    @FormUrlEncoded
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<LoginModel>

}