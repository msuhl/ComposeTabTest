package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.tabRowItems
import com.example.myapplication.screens.ShowWebView
import com.google.accompanist.pager.*
import com.google.accompanist.web.WebViewState
import com.google.accompanist.web.rememberWebViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Column(
                modifier = Modifier
            ) {
                TopScreen()
            }
        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopScreen(
){
    Scaffold(
        backgroundColor = Color.White,
        topBar = { },
    ) { paddingValues ->
        Surface(
            color = MaterialTheme.colors.background
        ) {
            Column(modifier = Modifier.padding(paddingValues)) {
                MyTabRow(
                )
            }
        }
    }


    }

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun MyTabRow(
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    Log.d("Svend ", "My tab row recompose")
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                color = MaterialTheme.colors.secondary
            )
        },
    ) {
        tabRowItems.forEachIndexed { index, item ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = "")
                },
                text = {
                    Text(
                        text = item.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
            )
        }
    }
    HorizontalPager(
        count = tabRowItems.size,
        state = pagerState,
    ) {
        tabRowItems[pagerState.currentPage].screen()
    }
}
