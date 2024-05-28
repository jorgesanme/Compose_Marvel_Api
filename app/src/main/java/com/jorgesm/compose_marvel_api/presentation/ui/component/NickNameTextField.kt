package com.jorgesm.compose_marvel_api.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NickNameTextField(modifier: Modifier) {
    var state by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = state,
        onValueChange = { state = it },
        label = { Text(text = "nick") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Magenta,
            unfocusedBorderColor = Color.DarkGray
        ),
        modifier = modifier
            .size(width = 160.dp, height = 52.dp)
            .padding(top = 8.dp, bottom = 4.dp)
            .background(Color.White)
    )

}

@Preview
@Composable
fun PreviewNick() {
    NickNameTextField(Modifier)
}