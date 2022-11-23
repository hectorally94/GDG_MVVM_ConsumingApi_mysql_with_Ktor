package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation.gdgscreens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgComponents.MyImage
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgnavigation.Gdgscreens
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavController) {
    SplashContent(navController = navController)
}
@Composable
fun SplashContent(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(3000L)
        navController.navigate(Gdgscreens.Addgdgmember.name)
    }
    // Image
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyImage(modifier = Modifier.size(300.dp))
            Spacer(modifier = Modifier.requiredHeight(30.dp))
            Text(text = "DevFest Team")
        }


}




