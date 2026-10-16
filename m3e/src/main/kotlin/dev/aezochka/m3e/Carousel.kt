@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.aezochka.m3e

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.carousel.CarouselItemScope
import androidx.compose.material3.carousel.CarouselState
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Carousel M3E (multi-browse): крупный фокус + уменьшенные соседи,
 * краешки намекают, что можно скроллить.
 */
@Composable
fun M3EMultiBrowseCarousel(
    itemCount: Int,
    modifier: Modifier = Modifier,
    preferredItemWidth: Dp = 160.dp,
    itemSpacing: Dp = 8.dp,
    carouselState: CarouselState = rememberCarouselState(itemCount = { itemCount }),
    itemContent: @Composable CarouselItemScope.(index: Int) -> Unit,
) {
    HorizontalMultiBrowseCarousel(
        state = carouselState,
        preferredItemWidth = preferredItemWidth,
        modifier = modifier.fillMaxWidth().height(180.dp),
        itemSpacing = itemSpacing,
    ) { index ->
        itemContent(index)
    }
}
