package com.jorgesm.compose_marvel_api.presentation.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jorgesm.compose_marvel_api.R


@Preview
@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            stringResource(R.string.loading_title),
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 160.dp),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.marvel_red_translucent)
        )
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            strokeWidth = 8.dp,
            color = colorResource(R.color.marvel_red_translucent)
        )
    }
}

@Composable
fun BlinkingText(text: String) {
    var isVisible by remember { mutableStateOf(true) }
    AnimatedVisibility(isVisible,
        enter = scaleIn(),
        exit = scaleOut()
        ) {
        Text(
            text,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 160.dp)
                .clickable(enabled = true) { isVisible = false },
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.marvel_red_translucent)
        )
    }


}