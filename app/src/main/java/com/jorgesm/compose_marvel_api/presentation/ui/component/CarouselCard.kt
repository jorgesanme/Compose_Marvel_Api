package com.jorgesm.compose_marvel_api.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    isPreviousArrowEnable: Boolean,
    navigateToDetail: (Long) -> Unit,
    searchPreviousList: () -> Unit,
    searchNewList: () -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0)
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
            ArrowButton(
                isEnable = isPreviousArrowEnable && pagerState.currentPage > 0,
                iconVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.main_previous_arrow_content_description),
            ) {
                scope.launch {
                    if (pagerState.currentPage > 0)
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    else if (pagerState.currentPage == 0) {
                        searchPreviousList()
                        scope.launch {
                            pagerState.scrollToPage(0)
                        }
                    }
                }
            }

            HorizontalPager(
                count = list.size,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.9f)
            ) { page ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .background(Color.Transparent)
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
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                ) {
                    CharacterItem(item = list[page], navigateToDetail)
                }
            }

            ArrowButton(
                isEnable = true /*pagerState.currentPage < pagerState.pageCount - 1*/,
                iconVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = stringResource(R.string.main_forward_arrow_content_description)
            ) {
                scope.launch {
                    if (pagerState.currentPage < pagerState.pageCount - 1)
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    else if (pagerState.currentPage == pagerState.pageCount - 1) {
                        searchNewList()
                        scope.launch {
                            pagerState.scrollToPage(0)
                        }
                    }
                }
            }
        }

        if (pagerState.currentPage >= pagerState.pageCount - 1) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Ver Más",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .clickable {
                            searchNewList()
                            scope.launch {
                                pagerState.scrollToPage(0)
                            }
                        },
                    textAlign = TextAlign.Center,
                    color = colorResource(R.color.marvel_red),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        PagerCircleIndicator(pagerState.pageCount, pagerState, scope)
    }
}



