package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgDomain.repository

import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgCommon.Resource
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgDomain.model.GdgModel
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.dataobject.PostDataObject
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation.PostsState
import kotlinx.coroutines.flow.Flow

interface DomainRepository {
    fun getgdgmembers(): Flow<Resource<List<GdgModel>>>
     fun creategetgdgmembers(postDataObject: PostDataObject): Flow<Resource<PostDataObject>>

      suspend fun addgdgmembers(name:String, description:String)
     suspend fun Detelegdgmembers(id:String)
     suspend fun editgdgmembers(id: String, name:String, description:String)
}