@file:OptIn(ExperimentalPagerApi::class)

package com.ecotup.ecotupapplication.ui.general.onboarding

import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.ui.theme.GreyLight
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@Preview()
@Composable
fun PreviewFunction() {
    val navController = rememberNavController()
    Surface(modifier = Modifier.fillMaxSize()) {
        OnboardingScreen(navController = navController)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavController) {
    val items = ArrayList<OnboardingData>()

    items.add(
        OnboardingData(
            R.drawable.onboarding1_image,
            "What is Ecotup?",
            "Ecotup, as an application that provides mobile-based waste transportation services, is here to make it easier for you to take real action for the environment."
        )
    )

    items.add(
        OnboardingData(
            R.drawable.onboarding2_image,
            "Eco-friendly Technology",
            "Using advanced technology, Ecotup brings eco-friendly concepts to your fingertips."
        )
    )

    items.add(
        OnboardingData(
            R.drawable.onboarding3_image,
            "Title 3",
            "Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface."
        )
    )
    val pagerState = rememberPagerState(
        pageCount = items.size,
        infiniteLoop = false,
        initialPage = 0
    )

    OnBoardingPager(
        item = items,
        pagerState = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        navController = navController
    )
}

@Composable
fun OnBoardingPager(
    item: List<OnboardingData>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Box(modifier = modifier.fillMaxSize(), Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HorizontalPager(
                state = pagerState
            ) { page ->
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = item[page].image),
                        contentDescription = item[page].title,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = item[page].title,
                        modifier = Modifier.padding(top = 50.dp),
                        color = GreenLight,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), //                       
                    )

                    Text(
                        text = item[page].desc,
                        modifier = Modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp),
                        color = GreenLight,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                        textAlign = TextAlign.Center,
                    )
                }
            }

            PagerIndicator(item.size, pagerState.currentPage)
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            BottomSection(pagerState.currentPage, navController)
        }
    }
}

@Composable
fun rememberPagerState(
    @IntRange(from = 0) pageCount: Int,
    @IntRange(from = 0) initialPage: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) initialPageOffset: Float = 0f,
    @IntRange(from = 1) initialOffscreenLimit: Int = 1,
    infiniteLoop: Boolean = false
): PagerState = rememberSaveable(
    saver = PagerState.Saver
) {
    PagerState(
        pageCount = pageCount,
        currentPage = initialPage,
        currentPageOffset = initialPageOffset,
        offscreenLimit = initialOffscreenLimit,
        infiniteLoop = infiniteLoop
    )
}

@Composable
fun PagerIndicator(
    size: Int,
    currentPage: Int
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 50.dp)
    ) {
        repeat(size) {
            Indicator(isSelected = it == currentPage)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(targetValue = if (isSelected) 25.dp else 10.dp)

    Box(
        modifier = Modifier
            .padding(1.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) GreenLight else GreyLight.copy(alpha = 0.5f)
            )
    )
}

@Composable
fun BottomSection(currentPager: Int, navController: NavController) {
    Row(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = if (currentPager != 2) Arrangement.SpaceBetween else Arrangement.Center
    ) {
        if (currentPager == 2) {
            Button(
                onClick = {
                    navController.navigate(Screen.OptionScreen.route)
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = GreenLight)
            ) {
                Text(
                    text = "Let's Started",
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 40.dp),
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}


