package com.example.prodoctorov.presentation.main_app.user

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.NoOpUpdate
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.prodoctorov.R
import com.example.prodoctorov.presentation.authorization.new_password.CreateNewPasswordScreen
import com.example.prodoctorov.ui.theme.DarkGray
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*
import java.lang.Math.abs

class SearchSpecialistFragment : Fragment() {

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
//                    SearchSpecialistScreen()
                    BottomSheet()
                }
            }
            isClickable = true
        }
    }

    companion object {
        fun newInstance() = SearchSpecialistFragment()
    }
}

enum class ExpandedType {
    HALF, FULL, COLLAPSED
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BottomSheet() {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    var expandedType by remember {
        mutableStateOf(ExpandedType.COLLAPSED)
    }
//    val oneHundred = with(LocalDensity.current) { 100.dp.toPx().toInt() }

    val height by animateIntAsState(
        when (expandedType) {
            ExpandedType.HALF -> screenHeight / 2
            ExpandedType.FULL -> (screenHeight * 0.85).toInt()
            ExpandedType.COLLAPSED -> 24
        }
    )
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            var isUpdated = false
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(height.dp)
                    .pointerInput(Unit) {
                        detectVerticalDragGestures(
                            onVerticalDrag = { change, dragAmount ->
                                change.consume()
                                if (!isUpdated) {
                                    expandedType = when {
                                        dragAmount < 0 && expandedType == ExpandedType.COLLAPSED -> {
                                            ExpandedType.HALF
                                        }
                                        dragAmount < 0 && expandedType == ExpandedType.HALF -> {
                                            ExpandedType.FULL
                                        }
                                        dragAmount > 0 && expandedType == ExpandedType.FULL -> {
                                            ExpandedType.HALF
                                        }
                                        dragAmount > 0 && expandedType == ExpandedType.HALF -> {
                                            ExpandedType.COLLAPSED
                                        }
                                        else -> {
                                            ExpandedType.FULL
                                        }
                                    }
                                    isUpdated = true
                                }
                            },
                            onDragEnd = {
                                isUpdated = false
                            }
                        )
                    }
                    .background(Color.Red)
                    .padding(start = 12.dp, end = 12.dp, top = 36.dp),
//                contentAlignment = Alignment.Center
            )
            {

                Text(
                    text = "Рекомендации",
                    color = Color.White,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 12.dp)
                )

                val listlist = listOf(
                    "xzcxc",
                    "zxcxcx",
                    "zxcxcxcxz",
                    "xzcxzcxzc",
                    "xzcxc",
                    "zxcxcx",
                    "zxcxcxcxz",
                    "xzcxzcxzc",
                    "xzcxc",
                    "zxcxcx",
                    "zxcxcxcxz",
                    "xzcxzcxzc",
                    "xzcxc",
                    "zxcxcx",
                    "zxcxcxcxz",
                    "xzcxzcxzc",
                    "xzcxc",
                    "zxcxcx",
                    "zxcxcxcxz",
                    "xzcxzcxzc",
                    "xzcxzcxzc",
                    "xzcxc",
                    "zxcxcx",
                    "zxcxcxcxz",
                    "xzcxzcxzc",
                    "xzcxc",
                    "zxcxcx",
                    "zxcxcxcxz",
                    "xzcxzcxzc",
                    "xzcxc",
                    "zxcxcx",
                    "zxcxcxcxz",
                    "xzcxzcxzc",
                    "xzcxc",
                    "zxcxcx",
                    "zxcxcxcxz",
                    "xzcxzcxzc",
                )

                LazyColumn() {
                    items(listlist) {
                        Text(text = it)
                    }
                }
            }
        },
        sheetPeekHeight = height.dp
    ) {
        ConstraintLayout(
            Modifier
                .fillMaxSize()
                .background(DarkGray)
                .padding(start = 12.dp, end = 12.dp, top = 12.dp)
        ) {
            val (title, searchTxtFld, filters) = createRefs()

            Text(
                text = "Поиск специалиста",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )

            TextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .padding(end = 44.dp)
                    .constrainAs(searchTxtFld) {
                        top.linkTo(title.bottom, margin = 12.dp)
                    }
            )
//
            Image(
                painter = painterResource(id = R.drawable.ic_filters),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(filters) {
                        top.linkTo(searchTxtFld.top)
                        bottom.linkTo(searchTxtFld.bottom)
                        end.linkTo(parent.end)
                    }
                    .clickable {
                        Toast
                            .makeText(context, "3 click", Toast.LENGTH_SHORT)
                            .show()
                    }
            )

            val singapore = LatLng(1.35, 103.87)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(singapore, 10f)
            }
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = singapore),
                    title = "Singapore",
                    snippet = "Marker in Singapore"
                )
            }

//            GoogleMap(
//                modifier = Modifier.fillMaxSize(),
//                uiSettings = MapUiSettings(zoomControlsEnabled = true),
//                properties = MapProperties(
//                    mapStyleOptions = MapStyleOptions.loadRawResourceStyle(
//                        context, R.drawable.ic_filters
//                    )
//                ),
//                cameraPositionState = CameraPositionState(
//                    CameraPosition(
//                        LatLng(22.5726,88.3639), 12f,0f,0f)
//                    )
//                )
        }
    }
}

//@Composable
//fun <T : View> AndroidView(
//    factory: (Context) -> T,
//    modifier: Modifier = Modifier,
//    update: (T) -> Unit = NoOpUpdate ) {
//
//}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchSpecialistScreen() {

    var swipeUp by remember { mutableStateOf(ScreenSwipe.MIDDLE) }

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val context = LocalContext.current

    val swipeableState = rememberSwipeableState(ScreenSwipe.MIDDLE)
    val squareSizePx1 = with(LocalDensity.current) { (screenHeight / 2).toPx() }
    val squareSizePx2 = with(LocalDensity.current) { screenHeight.toPx() }

    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(Color.Red)
            .swipeable(
                state = swipeableState,
                anchors = mapOf(
                    150f to ScreenSwipe.UP,
                    squareSizePx1 to ScreenSwipe.MIDDLE,
                    squareSizePx2 to ScreenSwipe.DOWN,

                    ),
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Vertical
            )
    ) {
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(0, swipeableState.offset.value.toInt())
                }
                .fillMaxWidth()
                .height(screenHeight)
                .background(Color.Green)
        ) {
        }

        Button(
            onClick = { },
            modifier = Modifier
                .offset {
                    IntOffset(
                        (screenWidth / 2)
                            .toPx()
                            .toInt() - 48, (swipeableState.offset.value.toInt() / 1.06).toInt()
                    )
                }
                .width(48.dp)
                .height(48.dp)
        ) {}
    }

    ConstraintLayout(
        modifier = Modifier
            .background(DarkGray)
            .padding(start = 12.dp, end = 12.dp, top = 12.dp)
            .height(if (swipeUp == ScreenSwipe.MIDDLE) (screenHeight / 2) else screenHeight)
            .fillMaxWidth()
    ) {
        val (title, searchTxtFld, filters) = createRefs()

        Text(
            text = "Поиск специалиста",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 12.dp)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .height(40.dp)
                .constrainAs(searchTxtFld) {
                    top.linkTo(title.bottom, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(filters.start, margin = 12.dp)
                }
        )
//
        Image(
            painter = painterResource(id = R.drawable.ic_filters),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(filters) {
                    top.linkTo(searchTxtFld.top)
                    bottom.linkTo(searchTxtFld.bottom)
                    end.linkTo(parent.end)
                }
                .clickable {
                    Toast
                        .makeText(context, "3 click", Toast.LENGTH_SHORT)
                        .show()
                }
        )
    }
}

enum class ScreenSwipe {
    UP, DOWN, MIDDLE
}


//    ConstraintLayout(
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//        val (map, list, button) = createRefs()
//
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.Red)
//                .constrainAs(map) {
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                    top.linkTo(parent.top)
//                    bottom.linkTo(if(swipeUp == ScreenSwipe.UP) list.top else parent.bottom)
//                }
//        ) {
//            Image(painter = painterResource(id = R.drawable.ic_closed_eye), contentDescription = null)
//        }
//
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.Green)
//                .constrainAs(list) {
//                    top.linkTo(if(swipeUp == ScreenSwipe.UP) map.bottom else parent.top)
//                    bottom.linkTo(parent.bottom)
//                }
//        ) {
//            Image(painter = painterResource(id = R.drawable.ic_closed_eye), contentDescription = null)
//
//
//        }
//
//    }


//    ConstraintLayout(
////        contentAlignment = Alignment.Center,
//        modifier = Modifier
//            .fillMaxSize()
//            .pointerInput(Unit) {
//                detectDragGestures(
//                    onDrag = { change, dragAmount ->
//                        change.consumeAllChanges()
//
//                        val (x, y) = dragAmount
//                        if (kotlin.math.abs(x) > kotlin.math.abs(y)) {
//                            when {
//                                x > 0 -> {
//                                    //right
//                                    direction = 0
//                                }
//                                x < 0 -> {
//                                    // left
//                                    direction = 1
//                                }
//                            }
//                        } else {
//                            when {
//                                y > 0 -> {
//                                    // down
//                                    direction = 2
//                                }
//                                y < 0 -> {
//                                    // up
//                                    direction = 3
//                                }
//                            }
//                        }
//
//                    },
//                    onDragEnd = {
//                        when (direction) {
//                            0 -> {}
//                            1 -> {}
//                            2 -> {
//                                Toast
//                                    .makeText(context, "3 click", Toast.LENGTH_SHORT)
//                                    .show()
//                                swipeUp = ScreenSwipe.DOWN
//                                // down swipe code here
//                            }
//                            3 -> {
//                                Toast
//                                    .makeText(context, "4 click", Toast.LENGTH_SHORT)
//                                    .show()
//                                swipeUp = ScreenSwipe.UP
//                                // up swipe code here
//                            }
//                        }
//                    }
//                )
//            }
//    )
//    {
//        val (map, list, button) = createRefs()
//
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.Red)
//                .constrainAs(map) {
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                    top.linkTo(parent.top)
//                    bottom.linkTo(if (swipeUp == ScreenSwipe.UP) list.top else parent.bottom)
//                }
//        ) {
//            Image(painter = painterResource(id = R.drawable.ic_closed_eye), contentDescription = null)
//        }
//
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.Green)
//                .constrainAs(list) {
//                    top.linkTo(if (swipeUp == ScreenSwipe.UP) map.bottom else parent.top)
//                    bottom.linkTo(parent.bottom)
//                }
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.ic_closed_eye),
//                contentDescription = null
//            )
//
//
//        }
//
//        Button(
//            onClick = { /*TODO*/ },
//            modifier = Modifier.constrainAs(button) {
//                start.linkTo(parent.start)
//                end.linkTo(parent.end)
//                top.linkTo(list.top)
//                bottom.linkTo(list.top)
//            }
//        ) {
//
//        }
//
//    }
//}


//    Box(contentAlignment = Alignment.Center, modifier = Modifier.wrapContentSize()) {
//        Button(
//            onClick = {
//                Toast.makeText(context, "short click", Toast.LENGTH_SHORT).show()
//            },
//            modifier = Modifier.combinedClickable(
//                onClick = { },
//                onLongClick = {
//                    Toast.makeText(context, "long click", Toast.LENGTH_SHORT).show()
//                })
//        ) {
//
//        }
//    }