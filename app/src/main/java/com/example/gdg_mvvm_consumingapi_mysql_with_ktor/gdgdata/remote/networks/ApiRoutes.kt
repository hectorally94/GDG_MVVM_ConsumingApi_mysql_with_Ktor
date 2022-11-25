package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.networks

object ApiRoutes {
    private const val BASE_URL = "http://192.168.0.75/jetpackCompose" //jOb IP

    const val GDGMEMBERS = "$BASE_URL/getdata.php?myJSON"
    const val GDGADDMEMBER="$BASE_URL/adddata.php?"

    const val GDGDELETEMEMBER="$BASE_URL/deleteData.php?"
    const val GDGUpdateMEMBER="$BASE_URL/editData.php?"

}