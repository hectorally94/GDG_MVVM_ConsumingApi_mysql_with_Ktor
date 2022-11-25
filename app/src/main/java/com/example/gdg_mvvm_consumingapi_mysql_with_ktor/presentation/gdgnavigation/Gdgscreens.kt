package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation.gdgnavigation

enum class Gdgscreens {
    Splash,
    Addgdgmember,
    Detailsmember;

    companion object{
        fun fromRoute(route:String?): Gdgscreens
            =when(route?.substringBefore("/")){
                Splash.name-> Splash
                Addgdgmember.name-> Addgdgmember
                Detailsmember.name-> Detailsmember
                null-> Splash
            else->throw IllegalArgumentException("Route $route Not recognize")
            }
    }
}