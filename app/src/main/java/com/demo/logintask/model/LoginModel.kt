package com.demo.logintask.model

import com.google.gson.annotations.SerializedName

data class LoginModel(
    val token: String?,
    val user: User?,
    @SerializedName("errors")
    val errors: Errors?,
    @SerializedName("error")
    val error: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("status_id")
    val statusId: Int?
){
    data class Errors(
        @SerializedName("email")
        val email: List<String>?,
        @SerializedName("password")
        val password: List<String>?
    )
}

