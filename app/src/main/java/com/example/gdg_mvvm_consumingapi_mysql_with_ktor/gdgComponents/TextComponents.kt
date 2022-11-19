package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyTextfield(
    modifier: Modifier = Modifier,
    text: String?,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {},

    ) {
    val keyboardController = LocalSoftwareKeyboardController.current

    if (text != null) {
        TextField(
            value = text,
            onValueChange = onTextChange,
            colors = TextFieldDefaults.textFieldColors(
                textColor= Color.Black,
                backgroundColor = Color.Transparent,
                cursorColor = Color(0xffAC7088),
                focusedIndicatorColor = Color(0xffAC7088),
                focusedLabelColor = Color(0xFFDEB6AB)
            ),
            maxLines = maxLine,
            label = { Text(text = label) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                onImeAction()
                keyboardController?.hide()
            }),

            modifier = modifier
        )
    }

}
