package com.example.gdgjetpackcomposeconsumingapi_msql.gdgservices

import com.example.gdgjetpackcomposeconsumingapi_msql.gdgModele.RequestModel
import com.example.gdgjetpackcomposeconsumingapi_msql.gdgModele.ResponseModel
import com.example.gdgjetpackcomposeconsumingapi_msql.gdgnetworks.ApiServiceImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.serializer
import org.json.JSONObject

interface ApiService {

    suspend fun getgdgmembers(): List<ResponseModel>
    suspend fun setgdgmembers(name:String,description:String)
    suspend fun Detelegdgmembers(id:String)
    suspend fun editgdgmembers(id: String,name:String, description:String)

    suspend fun creategetgdgmembers(productRequest: RequestModel): ResponseModel?

    companion object {
        fun create(): ApiService {
            return ApiServiceImpl(client = HttpClient(Android) {
                    // Logging
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    // JSON
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(json)
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
            )
        }

        private val json = kotlinx.serialization.json.Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false

        }
    }
}