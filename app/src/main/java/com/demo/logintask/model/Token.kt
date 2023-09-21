package com.demo.logintask.model

data class Token(
    val abilities: List<String>,
    val created_at: String,
    val expires_at: Any,
    val id: Int,
    val last_used_at: Any,
    val name: String,
    val tokenable_id: Int,
    val tokenable_type: String,
    val updated_at: String
)