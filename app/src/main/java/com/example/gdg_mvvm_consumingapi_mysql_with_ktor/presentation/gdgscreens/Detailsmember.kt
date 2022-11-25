package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation.gdgscreens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgComponents.MyButton
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgComponents.MyTextfield
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.services.ApiService
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.viewModels.GgdViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun Detailsmember(
    navController: NavController,
    idshared:String?,
    nameshared:String?,
    descriptionshared:String?
) {
    val viewmodel = hiltViewModel<GgdViewModel>()

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
        val context = LocalContext.current

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
            modifier = Modifier
                .width(120.dp)
                .height(70.dp),
            text = "Update Member",
            onClick = {
                if (textfullname.toString().isNotEmpty() && textSpecialization.toString().isNotEmpty()) {
                    if (idshared != null) {
                        textfullname.value?.let {
                            textSpecialization.value?.let { it1 ->
                                viewmodel.editgdgmembers(idshared,
                                    it,
                                    it1
                                )
                            }
                        }
                    }
                    textfullname.value = ""
                    textSpecialization.value = ""
                    Toast.makeText(context, "Member Edited",
                        Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}
