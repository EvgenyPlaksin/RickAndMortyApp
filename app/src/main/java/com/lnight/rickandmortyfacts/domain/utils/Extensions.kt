package com.lnight.rickandmortyfacts.domain.utils

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.MutableState

fun LazyListState.shouldLoadMore(): Boolean {
        return layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
}