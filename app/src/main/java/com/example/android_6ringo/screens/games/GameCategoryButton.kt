package com.example.android_6ringo.screens.games

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GameCategoryButton(selected: Boolean, onChange: () -> Unit, content: @Composable () -> Unit) {
    val borderColor = if(selected) MaterialTheme.colorScheme.onSurface.copy(alpha = .5f) else Color.Transparent
    Row(modifier = Modifier
        .clip(RoundedCornerShape(16.dp))
        .border(1.dp, borderColor,
            shape = RoundedCornerShape(16.dp))
        .background(MaterialTheme.colorScheme.surface)

        .clickable { onChange() }
        .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
        ) {
        content()
    }
}