package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DomainRepositoryImpl {

    override fun getPosts(): Flow<Resource<List<PostModel>>> = flow {
        try {
            emit(Resource.Loading())
            val posts = api.getPosts().map { it.toPost() }
            emit(Resource.Success(data = posts))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "Error"))
        }
    }

    override fun postPost(post: PostDto): Flow<Resource<PostDto>> = flow {
        try {
            emit(Resource.Loading())
            val postResult = api.postPost(post)!!
            emit(Resource.Success(data = postResult))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }
    }

}