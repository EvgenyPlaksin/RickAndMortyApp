package com.lnight.rickandmortyfacts.common

import androidx.compose.foundation.lazy.grid.LazyGridState

fun LazyGridState.shouldLoadMore(): Boolean {
        return layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
}