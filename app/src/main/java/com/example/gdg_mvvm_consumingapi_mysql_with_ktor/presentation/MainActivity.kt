package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation.MainViewModel
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation.ui.theme.GDG_MVVM_ConsumingApi_mysql_with_KtorTheme
import dagger.hilt.android.AndroidEntryPoint
import xyz.teamgravity.postsktorclient.presentation.ui.theme.PostsKtorClientTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewmodel = hiltViewModel<MainViewModel>()
            val state = viewmodel.state.value

            GDG_MVVM_ConsumingApi_mysql_with_KtorTheme() {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (state.posts != null) { // success
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(state.posts) {
                                Card(
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp, vertical = 2.5.dp)
                                        .fillMaxWidth(),
                                    backgroundColor = Color.White
                                ) {
                                    Text(
                                        text = it.title,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            }
                        }

                    } else {
                        if (state.loading) {
                            CircularProgressIndicator()
                        } else {
                            state.error?.let { Text(text = it) }
                        }
                    }
                }
            }
        }
    }
}