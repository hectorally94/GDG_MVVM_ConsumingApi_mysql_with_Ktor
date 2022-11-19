package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgnavigation.NavigationScreens
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation.ui.theme.GDG_MVVM_ConsumingApi_mysql_with_KtorTheme
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation.viewModels.GgdViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            GDG_MVVM_ConsumingApi_mysql_with_KtorTheme() {
                NavigationScreens()
            }
        }
    }
}

