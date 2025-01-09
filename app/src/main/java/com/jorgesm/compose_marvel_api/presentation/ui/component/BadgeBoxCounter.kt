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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jorgesm.compose_marvel_api.R

@Composable
fun BadBoxCounter(counter: String, title: String, imageVector: ImageVector) {

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End,
        modifier = Modifier,
        ) {
        BadgedBox(
            badge = {
                Text(
                    textAlign = TextAlign.End,
                    maxLines = 1,
                    text = counter,
                    color = colorResource(R.color.white)
                )
            },
            modifier = Modifier.padding(start = 4.dp,end = 8.dp, top = 4.dp)
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = stringResource(
                    R.string.badBoxCounter_icon_content_description,
                    title
                ),
                tint = colorResource(R.color.white)
            )
            Text(
                text = title,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(top = 48.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewBadgedBox() {
    BadBoxCounter(counter = "10", "Comics", Icons.Rounded.AccountCircle)
}