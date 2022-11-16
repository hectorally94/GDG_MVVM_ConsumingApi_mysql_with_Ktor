package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.dataobject

import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgDomain.model.GdgModel
import kotlinx.serialization.Serializable

@Serializable
data class PostDataObject(
    val id:String,
    val name: String,
    val description: String
)

fun PostDataObject.toPostUi(): GdgModel {
    return GdgModel(
        id = id,
        name = name,
        description = description
    )
}