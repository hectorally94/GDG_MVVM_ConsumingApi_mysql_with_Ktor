package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.networks

import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.networks.ApiRoutes.GDGADDMEMBER
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.networks.ApiRoutes.GDGDELETEMEMBER
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.networks.ApiRoutes.GDGUpdateMEMBER
import com.example.gdgjetpackcomposeconsumingapi_msql.gdgModele.RequestModel
import com.example.gdgjetpackcomposeconsumingapi_msql.gdgModele.ResponseModel
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
        return client.get() {
                url(ApiRoutes.GDGMEMBERS)
               }
    }
    override suspend fun creategetgdgmembers(gdgRequest: RequestModel): ResponseModel? {
        return try client.post<ResponseModel> {
                url(ApiRoutes.GDGMEMBERS)
                body = gdgRequest
            }



    override suspend fun addgdgmembers(name:String,description:String) {
            val url = GDGADDMEMBER
            val client = HttpClient()
            return client.post(url) {
                body = FormDataContent(Parameters.build {
                    append("name", name)
                    append("description", description)
                })
        }
    }
    override suspend fun Detelegdgmembers(id:String) {

            val url = GDGDELETEMEMBER
            val client = HttpClient()
            return client.post(url) {
                body = FormDataContent(Parameters.build {
                    append("id", id)
                })
            }
    }
    override suspend fun editgdgmembers(id: String,name:String, description:String) {

            val url = GDGUpdateMEMBER
            val client = HttpClient()
            return client.post(url) {
                body = FormDataContent(Parameters.build {
                    append("id", id)
                    append("name", name)
                    append("description", description)
                })
            }
    }


}