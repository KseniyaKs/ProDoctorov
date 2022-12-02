package com.example.prodoctorov.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.prodoctorov.R
import com.example.prodoctorov.ui.theme.WhiteAlpha50

@Composable
fun BackWithTitle(
    titleText: String,
    promptText: String? = null,
    modifier: Modifier = Modifier,
    backClick: () -> Unit,
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (back, title, prompt) = createRefs()

        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = stringResource(id = R.string.back),
            tint = Color.White,
            modifier = Modifier
                .padding(start = 8.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() })
                { backClick() }
                .constrainAs(back) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })

        Text(
            text = titleText,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.jost_medium_500)),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(title) {
                    top.linkTo(back.top)
                    bottom.linkTo(back.bottom)
                }
        )

        if (!promptText.isNullOrEmpty()) {
            Text(
                text = promptText,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.jost_regular_400)),
                color = WhiteAlpha50,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
                    .constrainAs(prompt) {
                        top.linkTo(title.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}