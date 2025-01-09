package com.jorgesm.compose_marvel_api.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.jorgesm.compose_marvel_api.R
import com.jorgesm.domain.model.Character
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarouselCard(
    list: List<Character>,
    navigateToDetail: (Long)-> Unit,
    searchNewList:() ->Unit
) {
    val pagerState = rememberPagerState(initialPage = 1)
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.main_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            textAlign = TextAlign.Center,
            color = colorResource(R.color.marvel_red),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                enabled = pagerState.currentPage > 0,
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = colorResource(R.color.marvel_red),
                    disabledContentColor = colorResource(R.color.marvel_red_opaque)
                ),
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.main_previous_arrow_content_description),
                )

            }
            HorizontalPager(
                count = list.size,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) { page ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.background(Color.Transparent)
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = 0.50f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                            alpha = lerp(
                                start = 0.50f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }
                        .shadow(
                            elevation = 8.dp,
                            shape = RectangleShape,
                            clip = false,
                            ambientColor = colorResource(R.color.marvel_red),
                            spotColor = colorResource(R.color.marvel_red),
                        ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White ),
                ) {
                    CharacterItem(item = list[page], navigateToDetail)
                }

            }
            IconButton(
                enabled = pagerState.currentPage < pagerState.pageCount -1,
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = colorResource(R.color.marvel_red),
                    disabledContentColor = colorResource(R.color.marvel_red_opaque)
                ),
                onClick = {
                    scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = stringResource(R.string.main_forward_arrow_content_description)
                )
            }
        }
        Row(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(start = 36.dp, end =  36.dp, top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(list.size) {
                val color = if (pagerState.currentPage == it)
                    colorResource(R.color.marvel_red)
                else
                    colorResource(R.color.marvel_red_opaque)
                Box(modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .size(20.dp)
                    .background(color)
                    .clickable {
                        scope.launch { pagerState.animateScrollToPage(it) }
                    }
                ) {}
            }
        }
    }
}


