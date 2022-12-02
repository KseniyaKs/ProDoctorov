package com.example.prodoctorov.presentation.authorization.new_password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prodoctorov.R
import com.example.prodoctorov.presentation.view.BackWithTitle
import com.example.prodoctorov.presentation.view.ButtonWithText
import com.example.prodoctorov.ui.theme.DarkGray
import com.example.prodoctorov.ui.theme.Lilac
import com.example.prodoctorov.ui.theme.MiddleGray
import com.example.prodoctorov.ui.theme.WhiteAlpha50

class CreateNewPasswordFragment : Fragment() {
    companion object {
        fun newInstance() = CreateNewPasswordFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    CreateNewPasswordScreen()
                }
            }
            isClickable = true
        }
    }
}

@Composable
fun CreateNewPasswordScreen() {

    val firstPassword = remember { mutableStateOf("") }
    val secondPassword = remember { mutableStateOf("") }

    var firstPasswordVisible by remember { mutableStateOf(false) }
    var secondPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGray)
            .padding(top = 18.dp, start = 12.dp, end = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BackWithTitle(
            titleText = stringResource(id = R.string.last_step),
            promptText = stringResource(id = R.string.create_password),
            backClick = {}
        )

        TextField(
            value = firstPassword.value,
            onValueChange = { firstPassword.value = it },
            textStyle = LocalTextStyle.current.copy(
                fontFamily = FontFamily(Font(R.font.jost_regular_400)),
                fontSize = 16.sp,
            ),
            visualTransformation = if (!firstPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.password),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.jost_regular_400)),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            trailingIcon = {
                if (firstPassword.value.isNotEmpty()) {
                    Image(
                        painter = if (!firstPasswordVisible) painterResource(id = R.drawable.ic_arrow_to_down)
                        else painterResource(id = R.drawable.ic_closed_eye),
                        contentDescription = if (firstPasswordVisible) "Hide password" else "Show password",
                        modifier = Modifier.clickable {
                            firstPasswordVisible = !firstPasswordVisible
                        }
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                placeholderColor = WhiteAlpha50,
                backgroundColor = MiddleGray,
                focusedIndicatorColor = DarkGray,
                errorIndicatorColor = Color.Red,
                cursorColor = Color.White
            ),
            shape = RoundedCornerShape(2.dp),
            modifier = Modifier
                .padding(top = 48.dp)
                .height(50.dp)
                .fillMaxWidth()
        )

        TextField(
            value = secondPassword.value,
            onValueChange = { secondPassword.value = it },
            textStyle = LocalTextStyle.current.copy(
                fontFamily = FontFamily(Font(R.font.jost_regular_400)),
                fontSize = 16.sp,
            ),
            visualTransformation = if (!secondPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.repeat_password),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.jost_regular_400)),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            trailingIcon = {
                if (secondPassword.value.isNotEmpty()) {
                    Image(
                        painter = if (!secondPasswordVisible) painterResource(id = R.drawable.ic_arrow_to_down)
                        else painterResource(id = R.drawable.ic_closed_eye),
                        contentDescription = if (secondPasswordVisible) "Hide password" else "Show password",
                        modifier = Modifier.clickable {
                            secondPasswordVisible = !secondPasswordVisible
                        }
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                placeholderColor = WhiteAlpha50,
                backgroundColor = MiddleGray,
                focusedIndicatorColor = DarkGray,
                errorIndicatorColor = Color.Red,
                cursorColor = Color.White
            ),
            shape = RoundedCornerShape(2.dp),
            modifier = Modifier
                .padding(top = 12.dp)
                .height(50.dp)
                .fillMaxWidth()
        )

        ButtonWithText(
            text = stringResource(id = R.string.continue_btn),
            textColor = if (firstPassword.value.isNotEmpty() && secondPassword.value.isNotEmpty()) Color.White else WhiteAlpha50,
            fontSize = 16.sp,
            radius = 40.dp,
            background = Lilac,
            enabled = (firstPassword.value.isNotEmpty() && secondPassword.value.isNotEmpty()),
            modifier = Modifier
                .padding(top = 167.dp)
                .fillMaxWidth()
        ) {

        }
    }
}

