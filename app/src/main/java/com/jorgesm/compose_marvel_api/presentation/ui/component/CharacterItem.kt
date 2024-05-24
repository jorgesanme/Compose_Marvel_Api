package com.jorgesm.compose_marvel_api.presentation.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jorgesm.compose_marvel_api.R
import com.jorgesm.compose_marvel_api.utils.Routes
import com.jorgesm.domain.model.Character

@Composable
fun CharacterItem(
    item: Character,
    navHostController: NavHostController
) {
    Card(
        Modifier
            .size(352.dp, 528.dp)
            .padding(horizontal = 24.dp)
            .clickable {
                item.id?.let {
                    navHostController.navigate(Routes.DetailScreen.addItemId(it))
                }
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(0.5.dp, Color.LightGray)
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
            AsyncImage(
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .size(180.dp)
                    .clip(RoundedCornerShape(45f)),
                model = item.thumbnail,
                contentDescription = "Hero Image",
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Justify,
                text = item.name.toString(), style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textDecoration = TextDecoration.Underline
                )
            )

            Text(
                text = item.description.toString(),
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
