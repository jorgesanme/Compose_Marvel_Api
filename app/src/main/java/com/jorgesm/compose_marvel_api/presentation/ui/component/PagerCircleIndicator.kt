package com.jorgesm.compose_marvel_api.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.jorgesm.compose_marvel_api.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerCircleIndicator(pageCount: Int, pagerState: PagerState, scope: CoroutineScope) {
    Row(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(start = 36.dp, end = 36.dp, top = 4.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration)
                colorResource(R.color.marvel_red)
            else
                colorResource(R.color.marvel_grey_opaque)
            Box(modifier = Modifier
                .padding(2.dp)
                .clip(CircleShape)
                .size(20.dp)
                .background(color)
                .clickable {
                    scope.launch {
                        pagerState.animateScrollToPage(iteration)
                    }
                }
            ) {}
        }
    }
}