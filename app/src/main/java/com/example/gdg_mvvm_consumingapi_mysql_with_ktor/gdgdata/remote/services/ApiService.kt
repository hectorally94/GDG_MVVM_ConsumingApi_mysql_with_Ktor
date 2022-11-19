package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.services

import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgDomain.model.GdgModel
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.dataobject.PostDataObject

interface ApiService {

    suspend fun getgdgmembers(): List<PostDataObject>
    suspend fun creategetgdgmembers(postDataObject: PostDataObject): PostDataObject?
    suspend fun addgdgmembers(name:String,description:String):String
    suspend fun Detelegdgmembers(id:String)
    suspend fun editgdgmembers(id: String,name:String, description:String)

}
