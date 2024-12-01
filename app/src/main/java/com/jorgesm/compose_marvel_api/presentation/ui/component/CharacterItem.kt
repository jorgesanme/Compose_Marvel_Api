package com.jorgesm.compose_marvel_api.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.jorgesm.compose_marvel_api.R
import com.jorgesm.compose_marvel_api.utils.Routes
import com.jorgesm.domain.model.Character

@Composable
fun CharacterItem(
    item: Character,
    navHostController: NavHostController
) {
 Column( modifier = Modifier
            .fillMaxSize().padding(24.dp)
            .clickable {
                item.id.let {
                    navHostController.navigate(Routes.DetailScreen.addItemId(it))
                }
            },
    ) {
        Row(
            modifier = Modifier
                .paint(
                    painter = painterResource(id = R.drawable.marvel_bg_short),
                    contentScale = ContentScale.Inside,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ConstraintLayout {
                val (image, favorite) = createRefs()
                AsyncImage(
                    modifier = Modifier
                        .padding(vertical = 18.dp)
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                        .size(180.dp)
                        .clip(RoundedCornerShape(18f)),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.thumbnail)
                        .crossfade(true)
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = "Hero Image",
                )
                FavoriteIcon(
                    isFavorite = item.isFavorite,
                    modifier = Modifier
                        .constrainAs(favorite) {
                            top.linkTo(image.top)
                            end.linkTo(image.end, margin = (-22).dp)
                        }
                        .padding(top = 8.dp, end = 12.dp)
                        .size(60.dp),
                    isDetails = false,
                    iconSize = Modifier.size(40.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Justify,
                text = item.name, style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textDecoration = TextDecoration.Underline
                )
            )

            Text(
                text = item.description,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
