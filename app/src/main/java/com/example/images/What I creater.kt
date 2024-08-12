package com.example.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.images.ui.theme.AndroidCounter
import androidx.compose.animation.core.*

@Composable
fun AndroidsImageScreen() {
    // not sure what these 2 lines mean
    var fallingLogos by remember { mutableStateOf<List<Int>>(emptyList()) }
    var androidsAmount by remember { mutableIntStateOf(AndroidCounter.androidAmount) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_image),
            contentDescription = "Androids",
            modifier = Modifier
                .size(100.dp)
                .background(color = Color.Transparent)
                .clickable {
                    AndroidCounter.androidAmount++
                    androidsAmount = AndroidCounter.androidAmount
                    fallingLogos = fallingLogos + (fallingLogos.size + 1)
                }
        )


        fallingLogos.forEachIndexed { index, _ ->
            val transitionState = remember { MutableTransitionState(0.dp) }
            transitionState.targetState = 1000.dp
            val transition = updateTransition(transitionState, label = "fallingLogo")
            val yPosition by transition.animateDp(
                transitionSpec = { tween(durationMillis = 3000, easing = LinearEasing) },
                label = "yPosition"
            ) { state -> state }
            LaunchedEffect(index) {
                transitionState.targetState = 1000.dp
            }
            Image(
                painter = painterResource(id = R.drawable.android_image),
                contentDescription = "Falling Android",
                modifier = Modifier
                    .size(50.dp)
                    .offset(y = yPosition)
                    .background(color = Color.Transparent)
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Androids: $androidsAmount",
            color = Color.Gray,
            fontSize = 24.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_AndroidsImageScreen() {
    AndroidsImageScreen()
}
