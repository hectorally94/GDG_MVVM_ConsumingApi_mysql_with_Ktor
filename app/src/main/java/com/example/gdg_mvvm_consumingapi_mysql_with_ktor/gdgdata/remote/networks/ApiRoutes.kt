package com.example.gdgjetpackcomposeconsumingapi_msql.gdgnetworks

object ApiRoutes {
    private const val BASE_URL = "http://192.168.0.49/jetpackCompose" //jOb IP

   // private const val BASE_URL = "http://192.168.0.102:/jetpackCompose" // my  IP

    const val GDGMEMBERS = "$BASE_URL/getdata.php?myJSON"
    const val GDGADDMEMBER="$BASE_URL/adddata.php?"

    const val GDGDELETEMEMBER="$BASE_URL/deleteData.php?"
    const val GDGUpdateMEMBER="$BASE_URL/editData.php?"

}