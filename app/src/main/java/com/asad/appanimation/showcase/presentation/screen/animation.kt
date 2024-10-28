package com.asad.appanimation.showcase.presentation.screen

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Scale
import java.io.File

@Composable
fun CustomImageLoader(
    modifier: Modifier,
    imageModifier: Modifier,
    url: String,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String? = null,
    colorFilter: ColorFilter? = null,
) {
    val context = LocalContext.current
    val imageUri = if (url.startsWith("http")) {
        url
    } else {
        Uri.fromFile(File(url))
    }

    val model = ImageRequest.Builder(context)
        .data(imageUri)
        .diskCacheKey(url)
        .memoryCacheKey(url)
        .scale(Scale.FILL)
        .crossfade(true)
        .crossfade(100)
        .build()

    Box(modifier = modifier) {
        AsyncImage(
            model = model,
            contentDescription = contentDescription,
            contentScale = contentScale,
            colorFilter = colorFilter,
            modifier = imageModifier
        )
    }
}

@Composable
fun CustomImageLoader2(
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    url: String,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String? = null,
    colorFilter: ColorFilter? = null,
) {
    val context = LocalContext.current
    val currentContentDescription = rememberUpdatedState(contentDescription)
    val currentColorFilter = rememberUpdatedState(colorFilter)
    val currentContentScale = rememberUpdatedState(contentScale)


    // Remember the ImageRequest to prevent it from recomposing unnecessarily
    val model = remember(url) {
        val imageUri = if (url.startsWith("http")) url else Uri.fromFile(File(url))
        ImageRequest.Builder(context)
            .data(imageUri)
            .diskCacheKey(url)
            .memoryCacheKey(url)
            .scale(Scale.FILL)
            .crossfade(true)
            .crossfade(100)
            .build()
    }

    Box(modifier = modifier) {
        AsyncImage(
            model = model,
            contentDescription = currentContentDescription.value,
            contentScale = currentContentScale.value,
            colorFilter = currentColorFilter.value,
            modifier = imageModifier
        )

    }
}


@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomImageLoaderPreview() {
//    val previewHandler = AsyncImagePreviewHandler {
//        FakeImage(color = 0xFFFFFF00.toInt())
//    }
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
//            CustomImageLoader(
//                modifier = Modifier,
//                imageModifier = Modifier
//                    .aspectRatio(1280f / 720f)
//                    .fillMaxWidth(),
//                url = "https://example.com/image.jpg"
//            )
//        }
//    }
}