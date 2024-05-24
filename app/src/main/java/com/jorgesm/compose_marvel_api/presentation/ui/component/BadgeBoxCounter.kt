package com.jorgesm.compose_marvel_api.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BadBoxCounter(counter: String, title: String, imageVector: ImageVector) {
val tintColor = Color(0x88EC1D24)
    if (counter != null) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {
            BadgedBox(
                badge = {
                    Text(
                        text = counter,
                        color = Color.White
                    )
                },
                modifier = Modifier.padding(horizontal = 12.dp),
            ) {
                Icon(imageVector = imageVector, contentDescription = "Icon $title", tint = tintColor)
                Text(text = title, color = Color.White, modifier = Modifier.padding(top= 48.dp))
            }
        }
    }
}

@Preview
@Composable
fun PreviewBadgedBox() {
    BadBoxCounter(counter = "10", "Comics", Icons.Rounded.AccountCircle)
}