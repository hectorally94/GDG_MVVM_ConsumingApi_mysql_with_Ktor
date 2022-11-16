package xyz.teamgravity.postsktorclient.data.remote

import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.dto.PostDto

interface PostsApi {

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com"
        const val POSTS_URL = "$BASE_URL/posts"
    }

    suspend fun getPosts(): List<PostDto>

    suspend fun postPost(post: PostDto): PostDto?
}