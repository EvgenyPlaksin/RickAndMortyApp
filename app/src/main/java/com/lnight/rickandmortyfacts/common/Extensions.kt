package com.lnight.rickandmortyfacts.common

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.shouldLoadMore(): Boolean {
        return layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
}