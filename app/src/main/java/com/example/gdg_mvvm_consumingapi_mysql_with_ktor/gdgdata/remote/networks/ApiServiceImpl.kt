package com.example.gdgjetpackcomposeconsumingapi_msql.gdgnetworks

import com.example.gdgjetpackcomposeconsumingapi_msql.gdgModele.RequestModel
import com.example.gdgjetpackcomposeconsumingapi_msql.gdgModele.ResponseModel
import com.example.gdgjetpackcomposeconsumingapi_msql.gdgnetworks.ApiRoutes.GDGADDMEMBER
import com.example.gdgjetpackcomposeconsumingapi_msql.gdgnetworks.ApiRoutes.GDGDELETEMEMBER
import com.example.gdgjetpackcomposeconsumingapi_msql.gdgnetworks.ApiRoutes.GDGUpdateMEMBER
import com.example.gdgjetpackcomposeconsumingapi_msql.gdgservices.ApiService
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

class ApiServiceImpl(
    private val client: HttpClient

) : ApiService {
    override suspend fun getgdgmembers(): List<ResponseModel> {
        return try {
            client.get() {
                url(ApiRoutes.GDGMEMBERS)
               }
        } catch (ex: RedirectResponseException) {
            // 3xx - responses
            println("Error ghost: ${ex.response.status.description}")
            emptyList()
        } catch (ex: ClientRequestException) {
            // 4xx - responses
            println("Error ghost: ${ex.response.status.description}")
            emptyList()
        } catch (ex: ServerResponseException) {
            // 5xx - response
            println("Error ghost: ${ex.response.status.description}")
            emptyList()
        }
    }

    override suspend fun setgdgmembers(name:String,description:String) {
        return try {
            val url = GDGADDMEMBER
            val client = HttpClient()
            return client.post(url) {
                body = FormDataContent(Parameters.build {
                    append("name", name)
                    append("description", description)
                })
            }
        } catch (ex: RedirectResponseException) {
            // 3xx - responses
            println("Error ghost: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            // 4xx - responses
            println("Error ghost: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            // 5xx - response
            println("Error ghost: ${ex.response.status.description}")
        }
    }
    override suspend fun Detelegdgmembers(id:String) {
        return try {
            val url = GDGDELETEMEMBER
            val client = HttpClient()
            return client.post(url) {
                body = FormDataContent(Parameters.build {
                    append("id", id)
                })
            }
        } catch (ex: RedirectResponseException) {
            // 3xx - responses
            println("Error ghost: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            // 4xx - responses
            println("Error ghost: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            // 5xx - response
            println("Error ghost: ${ex.response.status.description}")
        }
    }
    override suspend fun editgdgmembers(id: String,name:String, description:String) {
        return try {
            val url = GDGUpdateMEMBER
            val client = HttpClient()
            return client.post(url) {
                body = FormDataContent(Parameters.build {
                    append("id", id)
                    append("name", name)
                    append("description", description)
                })
            }
        } catch (ex: RedirectResponseException) {
            // 3xx - responses
            println("Error ghost: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            // 4xx - responses
            println("Error ghost: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            // 5xx - response
            println("Error ghost: ${ex.response.status.description}")
        }
    }


    override suspend fun creategetgdgmembers(gdgRequest: RequestModel): ResponseModel? {
        return try {
            client.post<ResponseModel> {
                url(ApiRoutes.GDGMEMBERS)
                body = gdgRequest
            }
        } catch (ex: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${ex.response.status.description}")
            null
        } catch (ex: ClientRequestException) {
            // 4xx - responses
            println("Error: ${ex.response.status.description}")
            null
        } catch (ex: ServerResponseException) {
            // 5xx - response
            println("Error: ${ex.response.status.description}")
            null
        }
    }
}