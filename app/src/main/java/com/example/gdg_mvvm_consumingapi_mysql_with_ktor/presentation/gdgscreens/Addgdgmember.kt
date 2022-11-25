package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation.gdgscreens

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.R
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgComponents.MyButton
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgComponents.MyImage
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgComponents.MyTextfield
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgDomain.model.GdgModel
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.viewModels.GgdViewModel
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.presentation.gdgnavigation.Gdgscreens

@Composable
fun Addgdgmember(
    navController: NavController
)
{
    val viewmodel = hiltViewModel<GgdViewModel>()
    val state = viewmodel.state.value
    val textfullname = remember { mutableStateOf("") }
    val textSpecialization = remember { mutableStateOf("") }

    val context = LocalContext.current
    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icon")
            },
            backgroundColor = Color(0xFFDADFE3))

        // Content
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
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
            Text("The textfield has this text: " + textfullname.value)

            MyButton(
                modifier = Modifier
                    .width(90.dp)
                    .height(60.dp),
                text = "Add",
                onClick = {
                    if (textfullname.toString().isNotEmpty() && textSpecialization.toString().isNotEmpty()) {

                        viewmodel.addgdgmembers(
                            textfullname.value,
                            textSpecialization.value
                        )

                        textfullname.value = ""
                        textSpecialization.value = ""
                        Toast.makeText(context, "Member Added",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }

        Divider(modifier = Modifier.padding(10.dp))
        if (state.posts != null) { // success
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.posts) {
                    Card(
                        modifier = Modifier
                            .clickable {
                                viewmodel.Detelegdgmembers(it.id)
                            }
                            .padding(horizontal = 10.dp, vertical = 2.5.dp)
                            .fillMaxWidth(),
                        backgroundColor = Color.White
                    ) {
                        Column(modifier = Modifier.padding( 15.dp)) {
                            Text(text = it.name,
                                style = MaterialTheme.typography.subtitle2)
                            Text(text = it.description, style = MaterialTheme.typography.subtitle1)
                            MyImage(modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)                       // clip to the circle shape
                                .border(2.dp, Color.White, CircleShape)
                                .clickable {
                                    navController.navigate(Gdgscreens.Detailsmember.name + "/${it.id}" + "/${it.name}" + "/${it.description}")
                                } )
                        }
                    }
                }
            }
        } else {
            if (state.loading) {
                CircularProgressIndicator(
                    modifier = Modifier.padding().align(alignment = Alignment.CenterHorizontally)
                )
            } else {
                state.error?.let { Text(text = it) }
            }
        }
        }
    }


