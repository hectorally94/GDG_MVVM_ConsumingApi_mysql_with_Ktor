package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.repository

import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgCommon.Resource
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgDomain.model.GdgModel
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgDomain.repository.DomainRepository
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.dataobject.PostDataObject
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.dataobject.toPostUi
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.services.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DomainRepositoryImpl (private val api: ApiService) : DomainRepository {
    override fun getgdgmembers(): Flow<Resource<List<GdgModel>>> = flow {
        try {
            emit(Resource.Loading())
            val posts = api.getgdgmembers().map { it.toPostUi() }
            emit(Resource.Success(data = posts))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "Error"))
        }
    }

    override fun creategetgdgmembers(postDataObject: PostDataObject): Flow<Resource<PostDataObject>> = flow {
        try {
            emit(Resource.Loading())
            val postResult = api.creategetgdgmembers(postDataObject)!!
            emit(Resource.Success(data = postResult))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }
    }

    override fun addgdgmembers(name: String, description: String) {
        TODO("Not yet implemented")
    }

    override fun Detelegdgmembers(id: String) {
        TODO("Not yet implemented")
    }

    override fun editgdgmembers(id: String, name: String, description: String) {
        TODO("Not yet implemented")
    }


}