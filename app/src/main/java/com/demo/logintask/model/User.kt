package com.demo.logintask.model

data class User(
    val active: Int,
    val company_id: Int,
    val created_at: String,
    val current_team_id: Any,
    val email: String,
    val email_verified_at: Any,
    val id: Int,
    val mobile: String,
    val name: String,
    val parent_id: Int,
    val profile_photo_path: Any,
    val profile_photo_url: String,
    val role_id: Int,
    val scheme_id: Int,
    val tokens: List<Token>,
    val two_factor_confirmed_at: Any,
    val updated_at: String
)