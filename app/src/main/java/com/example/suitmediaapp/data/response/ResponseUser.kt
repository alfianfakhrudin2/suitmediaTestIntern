package com.example.suitmediaapp.data.response

import com.example.suitmediaapp.data.model.User

data class ResponseUser(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<User>
)
