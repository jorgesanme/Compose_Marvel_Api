package com.jorgesm.compose_marvel_api.presentation.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jorgesm.compose_marvel_api.R
import com.jorgesm.compose_marvel_api.presentation.ui.component.BadBoxCounter
import com.jorgesm.compose_marvel_api.presentation.ui.component.FavoriteIcon
import com.jorgesm.compose_marvel_api.presentation.ui.component.NickNameTextField
import com.jorgesm.domain.model.Character

@SuppressLint("UnusedMaterial3ScaffoldPaddingPara@2@meter")
@Composable
fun DetailView(
    detailViewModel: DetailViewModel,
    navHostController: NavHostController,
    itemId: Int
) {
    detailViewModel.getCharacterById(itemId.toString())
    val character: Character by detailViewModel.characterDetail.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        Card(
            Modifier
                .size(400.dp, 600.dp)
                .padding(horizontal = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(0.5.dp, Color.LightGray)
        ) {

            Row(
                modifier = Modifier.paint(
                    painter = painterResource(id = R.drawable.marvel_bg_short),
                    contentScale = ContentScale.Inside
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                ConstraintLayout {
                    val (image, textId, textTitle, favorite, nickName) = createRefs()
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                            .constrainAs(textTitle) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(image.top)
                            }
                            .background(
                                color = Color(0x55111D24),
                                shape = RoundedCornerShape(5f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = character.name.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(horizontal = 16.dp),
                            textAlign = TextAlign.Center,
                            maxLines = 2
                        )
                    }
                    AsyncImage(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(170.dp)
                            .clip(RoundedCornerShape(15f))
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                            },
                        model = character.thumbnail,
                        contentScale = ContentScale.Fit,
                        contentDescription = "Character image"
                    )
                    FavoriteIcon(
                        isFavorite = character.isFavorite,
                        Modifier
                            .constrainAs(favorite) {
                                top.linkTo(image.top)
                                end.linkTo(image.end)
                            }
                            .clickable {
                                detailViewModel.setFavorite()
                                Log.i("Yo", "favorito = ${character.isFavorite}")
                            })
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color(0x88EC1D24),
                                shape = RoundedCornerShape(15f)
                            )
                            .size(width = 170.dp, height = 32.dp)
                            .constrainAs(textId) {
                                bottom.linkTo(image.bottom)
                                start.linkTo(image.start)
                                end.linkTo(image.end)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 8.dp),
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            ),
                            text = character.id.toString()
                        )
                    }
                    NickNameTextField(Modifier.constrainAs(nickName){
                        top.linkTo(image.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
                    
                }
                
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0x55111D24),
                        shape = RoundedCornerShape(5f)
                    ),
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    BadBoxCounter(
                        counter = character.comics.toString(),
                        title = "Comics",
                        imageVector = Icons.Rounded.Star
                    )
                    BadBoxCounter(
                        counter = character.events.toString(),
                        title = "Events",
                        imageVector = Icons.Rounded.DateRange
                    )
                    BadBoxCounter(
                        counter = character.series.toString(),
                        title = "Series",
                        imageVector = Icons.AutoMirrored.Filled.List
                    )
                    BadBoxCounter(
                        counter = character.stories.toString(),
                        title = "Stories",
                        imageVector = Icons.Default.AccountBox
                    )
                }

            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = character.description.toString(),
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(8.dp)
                )
            }


        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 16.dp)
        ) {

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "back icon",
                tint = Color.Gray,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(40.dp)
                    .alignByBaseline()
                    .clickable {
                        navHostController.navigateUp()
                    }
            )
        }
    }
}