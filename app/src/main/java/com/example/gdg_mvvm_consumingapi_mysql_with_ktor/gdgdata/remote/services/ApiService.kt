package com.example.gdgjetpackcomposeconsumingapi_msql.gdgservices

import com.example.gdgjetpackcomposeconsumingapi_msql.gdgModele.RequestModel
import com.example.gdgjetpackcomposeconsumingapi_msql.gdgModele.ResponseModel
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.networks.ApiServiceImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

interface ApiService {

    suspend fun getgdgmembers(): List<ResponseModel>
    suspend fun creategetgdgmembers(productRequest: RequestModel): ResponseModel?

    suspend fun addgdgmembers(name:String,description:String)
    suspend fun Detelegdgmembers(id:String)
    suspend fun editgdgmembers(id: String,name:String, description:String)

}
