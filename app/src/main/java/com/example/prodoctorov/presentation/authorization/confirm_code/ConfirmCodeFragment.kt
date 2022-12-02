package com.example.prodoctorov.presentation.authorization.confirm_code

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.prodoctorov.R
import com.example.prodoctorov.presentation.view.BackWithTitle
import com.example.prodoctorov.presentation.view.ButtonWithText
import com.example.prodoctorov.presentation.view.Countdown
import com.example.prodoctorov.ui.theme.DarkGray
import com.example.prodoctorov.ui.theme.Lilac
import com.example.prodoctorov.ui.theme.MiddleGray
import com.example.prodoctorov.ui.theme.WhiteAlpha50

class ConfirmCodeFragment : Fragment() {

    companion object {
        fun newInstance() = ConfirmCodeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    AuthorizationConfirmCodeScreen()
                }
            }
            isClickable = true
        }
    }
}

@Composable
fun AuthorizationConfirmCodeScreen() {
    val max = 60
    val code = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGray)
            .padding(top = 18.dp, start = 12.dp, end = 12.dp),
        horizontalAlignment = CenterHorizontally
    ) {

        BackWithTitle(
            titleText = stringResource(id = R.string.password_recovery),
            promptText = stringResource(id = R.string.send_sms_to_number),
            backClick = { }
        )


        TextField(
            value = code.value,
            onValueChange = { code.value = it },
            textStyle = LocalTextStyle.current.copy(
                fontFamily = FontFamily(Font(R.font.jost_regular_400)),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                backgroundColor = MiddleGray,
                focusedIndicatorColor = DarkGray,
                errorIndicatorColor = Color.Red,
                cursorColor = Color.White
            ),
            shape = RoundedCornerShape(2.dp),
            modifier = Modifier
                .padding(top = 48.dp)
                .height(50.dp)
                .width(150.dp)
        )

        ButtonWithText(
            text = stringResource(id = R.string.continue_btn),
            textColor = if (code.value.isNotEmpty()) Color.White else WhiteAlpha50,
            fontSize = 16.sp,
            radius = 40.dp,
            background = Lilac,
            enabled = code.value.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 171.dp)
        ) {

        }

        Countdown(
            max = max,
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = 24.dp)
                .align(CenterHorizontally),
            onResendConfirmCode = {}
        )
    }
}

