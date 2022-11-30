package com.example.shimmereffect

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shimmereffect.ui.theme.ShimmerEffectTheme

@Composable
fun ShimmerEffect() {
    val colors = listOf(
        Color.Blue.copy(alpha = 0.7f),
        Color.Blue.copy(alpha = 0.5f),
        Color.Blue.copy(alpha = 0.7f)
    )

    val transition = rememberInfiniteTransition()
    val shimmerAnimation = transition.animateFloat(initialValue = 0f, targetValue = 800f , animationSpec = InfiniteRepeatableSpec(
        animation = tween(1000),
        repeatMode = RepeatMode.Reverse
    )
    )

    val brush = Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = Offset(shimmerAnimation.value, shimmerAnimation.value)
    )
    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(20){
            ShimmerItem(brush = brush)
        }
    }
}

@Composable
fun ShimmerItem(brush: Brush) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(brush)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShimmerEffectTheme {
        ShimmerItem(brush = Brush.linearGradient(colors = listOf(
            Color.Blue.copy(alpha = 0.7f),
            Color.Blue.copy(alpha = 0.5f),
            Color.Blue.copy(alpha = 0.7f)
        )))
    }
}