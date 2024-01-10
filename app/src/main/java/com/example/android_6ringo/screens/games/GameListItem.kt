package com.example.android_6ringo.screens.games

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.entities.Game
import com.example.android_6ringo.helpers.LONG_DATE_FORMAT
import com.example.android_6ringo.helpers.formatGameStatus
import com.example.android_6ringo.helpers.getStatusColor


@Composable
fun GameListItem (game: Game) {
    val controller = LocalComposeContext.current.navController
    GameListItem(game, onClick = {
        controller.navigate("games/${game._id}")
    })
}
@Composable
fun GameListItem (game: Game, onClick: (game: Game) -> Unit){
    val statusBgColor =
    Surface(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(game) }
        .clip(RoundedCornerShape(16.dp))) {
        Column {
            Box() {
                Box(modifier= Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)) {
                    AsyncImage(game.article!!.images[0].thumbnailUrl,
                        "Article image",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White))
                }


                Text(text = game.status.formatGameStatus(), style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(game.getStatusColor())
                        .padding(8.dp)
                )
            }


            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {
                Text(text = game.article?.name.toString().trim() , style = MaterialTheme.typography.titleMedium)
                
                if(game.status.uppercase() == "ON_GOING") {
                    Text(text = "S'achève le ${game.endDate.format(LONG_DATE_FORMAT)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(.5f)
                        )
                }else if(game.status.uppercase() == "UPCOMING") {
                    Text(text = "Débute le ${game.startDate.format(LONG_DATE_FORMAT)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(.5f)
                    )
                }
            }
        }
    }
}