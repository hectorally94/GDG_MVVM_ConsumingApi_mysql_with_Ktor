package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation

import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgDomain.model.GdgModel


data class PostsState(
    val posts: List<GdgModel>? = null,
    val loading: Boolean = false,
    val error: String? = null
)
