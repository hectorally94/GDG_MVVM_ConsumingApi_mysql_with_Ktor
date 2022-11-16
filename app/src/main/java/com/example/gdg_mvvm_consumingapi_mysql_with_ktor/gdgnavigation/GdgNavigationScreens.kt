package com.example.gdgjetpackcomposeconsumingapi_msql.gdgnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.gdgjetpackcomposeconsumingapi_msql.gdgscreens.Addgdgmember
import com.example.gdgjetpackcomposeconsumingapi_msql.gdgscreens.Detailsmember
import com.example.gdgjetpackcomposeconsumingapi_msql.gdgscreens.Splash

@Composable
fun NavigationScreens(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = Gdgscreens.Splash.name){

      composable(Gdgscreens.Splash.name){
          Splash(navController=navController)
      }
        composable(Gdgscreens.Addgdgmember.name){
            Addgdgmember(navController=navController)
        }

        ////
        composable(Gdgscreens.Detailsmember.name+"/{id}" +"/{name}" +"/{description}",
            arguments = listOf(navArgument( "id") {type = NavType.StringType},
                              navArgument( "name") {type = NavType.StringType},
                            navArgument( "description") {type = NavType.StringType}
            ))
        {
                backStackEntry ->
            Detailsmember(navController = navController,
                backStackEntry.arguments?.getString("id"),
                backStackEntry.arguments?.getString("name"),
                backStackEntry.arguments?.getString("description")
            )
        }
    }
}