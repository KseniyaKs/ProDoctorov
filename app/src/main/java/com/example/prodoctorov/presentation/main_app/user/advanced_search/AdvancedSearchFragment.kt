package com.example.prodoctorov.presentation.main_app.user.advanced_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.prodoctorov.R
import com.example.prodoctorov.presentation.view.BackWithTitle
import com.example.prodoctorov.presentation.view.ButtonWithText
import com.example.prodoctorov.ui.theme.*

class AdvancedSearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_advanced_search, container, false)
        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                androidx.compose.material3.MaterialTheme {
//                    SearchSpecialistScreen()
                    AdvancedSearchScreen()
                }
            }
            isClickable = true
        }
    }

    companion object {
        fun newInstance() = AdvancedSearchFragment()
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AdvancedSearchScreen() {

    val fullName = remember { mutableStateOf("") }
    var sliderPosition by remember { mutableStateOf(0f) }
    val keyboardController = LocalSoftwareKeyboardController.current

    val itemList = listOf(
        "Стоматолог",
        "Хирург",
        "Офтальмолог",
        "Отоларинголог",
        "Удаление зуба",
        "Лечение кариеса",
        "Протезирование",
        "Установка пломб"
    )



    ConstraintLayout(
        modifier = Modifier
            .background(DarkGray)
            .padding(all = 12.dp)
    ) {
        val (back, fullNameTxt, specializationTxt, servicesTxt, radiusTxt, radiusImg, distance, buttons) = createRefs()

        BackWithTitle(
            titleText = "Расширенный поиск",
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(back) { top.linkTo(parent.top) }
        ) {

        }

        TextField(
            value = fullName.value,
            onValueChange = { fullName.value = it },
            textStyle = LocalTextStyle.current.copy(
                fontFamily = FontFamily(Font(R.font.jost_regular_400)),
                fontSize = 16.sp
            ),
            placeholder = {
                Text(
                    text = "ФИО",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.jost_regular_400))
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = White,
                focusedIndicatorColor = MiddleGray,
                backgroundColor = MiddleGray,
                cursorColor = White,
                placeholderColor = WhiteAlpha50
            ),
            shape = RoundedCornerShape(2.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .constrainAs(fullNameTxt) {
                    top.linkTo(back.bottom, margin = 24.dp)
                }
        )

        SearchDropDownMenu(
            title = "Специализация",
            itemList = itemList,
            modifier = Modifier
                .constrainAs(specializationTxt) {
                    top.linkTo(fullNameTxt.bottom, margin = 12.dp)
                }
        )

        SearchDropDownMenu(
            title = "Услуги",
            itemList = itemList,
            modifier = Modifier
                .constrainAs(servicesTxt) {
                    top.linkTo(specializationTxt.bottom, margin = 12.dp)
                }
        )

        Text(
            text = "Радиус поиска",
            fontFamily = FontFamily(Font(R.font.jost_regular_400)),
            fontSize = 16.sp,
            color = White,
            modifier = Modifier.constrainAs(radiusTxt) {
                top.linkTo(servicesTxt.bottom, margin = 24.dp)
                start.linkTo(parent.start)
            })

        Text(
            text = "${sliderPosition.toInt()} km",
            fontFamily = FontFamily(Font(R.font.jost_semibold_600)),
            fontSize = 16.sp,
            color = White,
            modifier = Modifier.constrainAs(distance) {
                top.linkTo(servicesTxt.bottom, margin = 24.dp)
                end.linkTo(parent.end)
            })


        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..100f,
            colors = SliderDefaults.colors(
                thumbColor = White
            ),
//            thumb = {
//                Image(painterResource(id = R.drawable.ic_slider_thumb),null)
//            },
            modifier = Modifier.constrainAs(radiusImg) {
                top.linkTo(radiusTxt.bottom, margin = 12.dp)
            }
        )

        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenHeightDp.dp

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.constrainAs(buttons) {
                bottom.linkTo(parent.bottom, margin = 12.dp)
            }
        ) {
            ButtonWithText(
                text = "Сбросить",
                fontSize = 14.sp,
                textColor = White,
                background = MiddleGray,
                radius = 20.dp,
                modifier = Modifier.weight(1F)
            ) {

            }
            ButtonWithText(
                text = "Применить",
                fontSize = 14.sp,
                textColor = White,
                background = Lilac,
                radius = 20.dp,
                modifier = Modifier.weight(1F)
            ) {

            }
        }


    }
}

@Composable
fun SearchDropDownMenu(
    title: String,
    itemList: List<String>,
    modifier: Modifier = Modifier
) {

    var expanded by remember { mutableStateOf(false) }

    var selectedItem by remember { mutableStateOf(mutableListOf<String>()) }

    val list = remember { mutableStateListOf<String>() }
    var boxSize by remember { mutableStateOf(Size.Zero) }

    Box(
        modifier = modifier
            .height(50.dp)
            .background(MiddleGray, RoundedCornerShape(2.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { expanded = true }
            .fillMaxWidth()
            .onGloballyPositioned { layoutCoordinates ->
                boxSize = layoutCoordinates.size.toSize()
            }
    ) {
        Text(
            text = "$title: ${if (list.isEmpty()) "" else list.joinToString(separator = ", ") { it }}",
            fontFamily = FontFamily(Font(R.font.jost_regular_400)),
            fontSize = 16.sp,
            color = White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterStart)
                .padding(start = 12.dp, end = 30.dp)
        )
        Image(
            painter = painterResource(id = if (expanded) R.drawable.ic_arrow_to_up else R.drawable.ic_arrow_to_down),
            contentDescription = null,
            alignment = Alignment.CenterEnd,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterEnd)
                .padding(end = 12.dp)
        )

//        MaterialTheme(
//            colors = MaterialTheme.colors.copy(surface = DarkGray),
//            shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(20.dp))
//        ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(0.dp, 12.dp),
            modifier = Modifier
//                    .padding(top = 12.dp)
                .height(188.dp)
                .background(LightGray)
                .width(with(LocalDensity.current) { boxSize.width.toDp() })
        ) {
            itemList.forEach {
                DropdownMenuItem(
                    onClick = {
                        if(list.contains(it)) list.remove(it) else list.add(it)
                        selectedItem = list
                    }
                ) {
                    asd(text = it, select = list.contains(it))
                }
            }
        }
    }
}

@Composable
fun asd(text: String, select: Boolean) {
    ConstraintLayout() {
        val (textPos, line, selectPos) = createRefs()
        Text(
            text,
            color = White,
            modifier = Modifier.constrainAs(textPos) {
                start.linkTo(parent.start)
            }
        )

        Divider(
            color = LightGray_2,
            thickness = 1.dp,
            modifier = Modifier.constrainAs(line) {
                top.linkTo(textPos.bottom, margin = 12.dp)
            }
        )
        if (select) {
            Image(
                painter = painterResource(id = R.drawable.ic_selected_item),
                contentDescription = null,
                modifier = Modifier.constrainAs(selectPos) {
                    end.linkTo(parent.end)
                }
            )
        }
    }
}
