package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgDomain.model.GdgModel
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation.gdgscreens.Addgdgmember
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation.gdgscreens.Detailsmember
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation.gdgscreens.Splash
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation.viewModels.GgdViewModel
import com.example.gdgjetpackcomposeconsumingapi_msql.gdgnavigation.Gdgscreens

@Composable
fun NavigationScreens(ggdViewModel: GgdViewModel){
    val navController= rememberNavController()

    NavHost(navController = navController, startDestination = Gdgscreens.Splash.name){

      composable(Gdgscreens.Splash.name){
          Splash(navController=navController)
      }
        composable(Gdgscreens.Addgdgmember.name){
            Addgdgmember(
                navController =navController,
                gdgmembers = emptyList<GdgModel>(),
                onAddgdgmember = { name, description -> name + description },
                onRemovegdgmember={ id ->id}
            )

            NoteScreen(notes = notesList,
                onRemoveNote = { ggdViewModel.removeNote(it) },
                onAddNote = { ggdViewModel.addNote(it) }
            )

        }
        ////
        composable(
            Gdgscreens.Detailsmember.name+"/{id}" +"/{name}" +"/{description}",
            arguments = listOf(navArgument( "id") {type = NavType.StringType},
                              navArgument( "name") {type = NavType.StringType},
                            navArgument( "description") {type = NavType.StringType}
            ))
        {
                backStackEntry ->
            Detailsmember(
                navController = navController,
                backStackEntry.arguments?.getString("id"),
                backStackEntry.arguments?.getString("name"),
                backStackEntry.arguments?.getString("description"),
                onEditgdgmember= { ggdViewModel.editgdgmembers("","","") }

            )
            // onEditgdgmember = {id, name, description -> id + name + description },
        }

    }
}



