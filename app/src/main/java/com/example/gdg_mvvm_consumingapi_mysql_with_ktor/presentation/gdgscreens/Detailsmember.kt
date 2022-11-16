package com.example.gdgjetpackcomposeconsumingapi_msql.gdgscreens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gdgjetpackcomposeconsumingapi_msql.gdgComponents.MyButton
import com.example.gdgjetpackcomposeconsumingapi_msql.gdgComponents.MyTextfield
import com.example.gdgjetpackcomposeconsumingapi_msql.gdgservices.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun Detailsmember(
           navController: NavController,
             idshared:String?,nameshared:String?,descriptionshared:String?) {

DetailsmemberContent(navController = navController,idshared,nameshared,descriptionshared)
}

@Composable
private fun DetailsmemberContent(navController: NavController, idshared:String?,nameshared:String?,descriptionshared:String?) {
    val coroutineScope = rememberCoroutineScope()
    if (idshared != null) {
        Log.d("idshared",idshared)
    }
    if (nameshared != null) {
        Log.d("nameshared",nameshared)
    }
    if (descriptionshared != null) {
        Log.d("descriptionshared",descriptionshared)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
    ) {
        var textfullname = remember { mutableStateOf(nameshared) }
        val textSpecialization = remember { mutableStateOf(descriptionshared) }

        MyTextfield(
            modifier = Modifier
                .fillMaxWidth(),
            text = textfullname.value,
            label = "Edit Full Name",
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
            modifier =Modifier.width(120.dp).height(70.dp),
            text = "Update Member",
            onClick = {
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        try {
                            if (idshared != null) {
                                ApiService.create().editgdgmembers(idshared,textfullname.value.toString(),textSpecialization.value.toString())
                            }
                        } catch (e: Exception) {
                            // handle exception
                        }
                    }
                }
            }
        )
    }
}