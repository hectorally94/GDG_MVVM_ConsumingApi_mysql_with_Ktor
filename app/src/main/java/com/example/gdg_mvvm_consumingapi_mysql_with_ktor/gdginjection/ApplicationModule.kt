package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdginjection

import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgDomain.repository.DomainRepository
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.networks.ApiServiceImpl
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.services.ApiService
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.repository.DomainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        // JSON
        install(JsonFeature) {
            serializer = KotlinxSerializer()
            //or serializer = KotlinxSerializer() the default is json
            acceptContentTypes = acceptContentTypes + ContentType.Any
        }
        // Timeout
        install(HttpTimeout) {
            requestTimeoutMillis = 15000L
            connectTimeoutMillis = 15000L
            socketTimeoutMillis = 15000L
        }
        // Apply to all requests
        defaultRequest {
            // Parameter("api_key", "some_api_key")
            // Content Type
            if (method != HttpMethod.Get) contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }
    @Provides
    @Singleton
    fun provideApi(client: HttpClient): ApiService = ApiServiceImpl(client)
    @Provides
    fun provideRepository(api: ApiService): DomainRepository = DomainRepositoryImpl(api)

}