package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgDomain.repository

import kotlinx.coroutines.flow.Flow

interface DomainRepository {
    fun getPosts(): Flow<Resource<List<PostModel>>>

    fun postPost(post: PostDto): Flow<Resource<PostDto>>
}