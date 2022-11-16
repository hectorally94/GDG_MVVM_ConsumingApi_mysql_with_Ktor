package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation.gdgscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgComponents.MyButton
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgComponents.MyImage
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgComponents.MyTextfield
import com.example.gdgjetpackcomposeconsumingapi_msql.gdgnavigation.Gdgscreens
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.services.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun Addgdgmember(navController: NavController) {
    AddgdgmemberContent(navController = navController)
}

@Composable
private fun AddgdgmemberContent(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
    ) {
        var textfullname = remember { mutableStateOf("") }
        val textSpecialization = remember { mutableStateOf("") }

        MyTextfield(
            modifier = Modifier
                .fillMaxWidth(),
            text = textfullname.value,
            label = "Full Name",
            onTextChange = {
                if (it.all { char ->
                        char.isLetter() || char.isWhitespace()
                    }) textfullname.value = it
            })
        Spacer(modifier = Modifier.padding(10.dp))
        MyTextfield(
            modifier = Modifier
                .fillMaxWidth(),
            text = textSpecialization.value,
            label = "Specialization",
            onTextChange = {
                if (it.all { char ->
                        char.isLetter() || char.isWhitespace()
                    }) textSpecialization.value = it
            })
        Spacer(modifier = Modifier.padding(10.dp))
        Text("The textfield has this text: " + textfullname.value)
        MyButton(
            modifier = Modifier
                .width(120.dp)
                .height(70.dp),
            text = "Add Member",
            onClick = {
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        try {
                            ApiService.create().setgdgmembers(textfullname.value,textSpecialization.value)
                        } catch (e: Exception) {
                            // handle exception
                        }
                    }
                }
            }
        )
    Displaydata(navController)
    }
}
@Composable
fun Displaydata( navController: NavController){
    val coroutineScope = rememberCoroutineScope()

    val apiService by lazy {
        ApiService.create()
    }
    var members = produceState(
        initialValue = emptyList<ResponseModel>(),
        producer = {
            value = apiService.getgdgmembers()
        }
            )
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        LazyColumn() {

            items(members.value) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            10.dp
                        )
                        .clickable {
                            // Log.d( "my value is ", it.id)
                            coroutineScope.launch {
                                withContext(Dispatchers.IO) {
                                    try {
                                        ApiService
                                            .create()
                                            .Detelegdgmembers(it.id)
                                    } catch (e: Exception) {
                                        // handle exception
                                    }
                                }
                            }
                        }
                        .background(Color.Gray)) {
                    Column() {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(text = "Full name: ${it.name}",
                                style = MaterialTheme.typography.caption)
                            Text(text = "Specialization: ${it.description}",
                                style = MaterialTheme.typography.caption)
                            MyImage(modifier = Modifier.size(50.dp).clip(CircleShape)                       // clip to the circle shape
                                .border(2.dp, Color.White, CircleShape)
                                .clickable {
                                    navController.navigate(Gdgscreens.Detailsmember.name + "/${it.id}" + "/${it.name}" + "/${it.description}")
                                } )
                        }
                    }

                }

            }
        }
    }
}

