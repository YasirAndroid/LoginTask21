package com.demo.logintask.model

import com.google.gson.annotations.SerializedName

data class RegisterModel(
    val token: String?,
    val user: UserX?,
    @SerializedName("errors")
    val errors: Errors?,
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
        @SerializedName("mobile")
        val mobile: List<String>?,
        @SerializedName("name")
        val name: List<String>?,
        @SerializedName("password")
        val password: List<String>?
    )
}