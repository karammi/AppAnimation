package com.asad.appanimation.showcase.presentation.screen

import android.os.Environment
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asad.appanimation.showcase.presentation.viewModel.AnimationShowcaseViewModel
import java.io.File
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalContext
import coil3.ImageLoader
import coil3.request.ImageRequest
import kotlinx.coroutines.*

fun getImageFilesFromDirectory(path: String): List<String> {
    val directory = File(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
        path
//        "app_animation_folder/FUE-Consolation"
//        "app_animation_folder/FirstUser_Background"
//        "app_animation_folder/06-Nod"
        ///storage/emulated/0/Download/app_animation_folder/FUE-Consolation
        ///storage/emulated/0/Download/app_animation_folder/FirstUser_Background
        ///storage/emulated/0/Download/app_animation_folder/06-Nod
    )

    return directory.listFiles()
        ?.filter { it.isFile && it.extension.lowercase() == "png" } // Filter for PNG images
        ?.map { it.absolutePath }
        ?.sorted()// Map to file paths
        ?: emptyList() // Return empty list if no files found
}

@Composable
fun AnimationShowcaseScreen(
    path: String,
    viewModel: AnimationShowcaseViewModel = hiltViewModel()
) {
    AnimationShowcaseContent(
        path = path
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimationShowcaseContent(
    modifier: Modifier = Modifier,
    path: String = ""
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "App Animation Showcase") })
        }
    ) { paddingValue ->
        Column(modifier = Modifier.padding(paddingValue)) {
            AnimatedImageSequence(getImageFilesFromDirectory("app_animation_folder/FUE-Consolation"))
//            AnimatedImageSequence6(imageUrls = getImageFilesFromDirectory("app_animation_folder/FUE-Consolation"))
//            AnimatedImageSequence5(imageUrls = getImageFilesFromDirectory("app_animation_folder/FUE-Consolation"))
//            AnimatedImageSequence5(imageUrls = getImageFilesFromDirectory("app_animation_folder/FirstUser_Background"))
//            AnimatedImageSequence5(imageUrls = getImageFilesFromDirectory("app_animation_folder/06-Nod"))
        }

    }
}


@Composable
fun AnimatedImageSequence(
    imageUrls: List<String>,          // List of image URLs for the animation
    frameDurationMillis: Long = 100L  // Duration to display each frame
) {
    // Keep track of the current frame index
    var currentFrameIndex by remember { mutableStateOf(0) }

    // Coroutine to animate frames
    LaunchedEffect(imageUrls) {
        while (isActive) {
            delay(frameDurationMillis) // Control frame rate
            currentFrameIndex = (currentFrameIndex + 1) % imageUrls.size // Loop frames
        }
    }

    // Display the current frame
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        CustomImageLoader(
            url = imageUrls[currentFrameIndex],
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(400.dp),
            imageModifier = Modifier
                .aspectRatio(1280f / 720f)
                .fillMaxWidth(),
        )
    }
}

@Composable
fun preloadImages(imageUrls: List<String>) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context).build()

    LaunchedEffect(imageUrls) {
        imageUrls.forEach { url ->
            val request = ImageRequest.Builder(context)
                .data(url)
                .build()
            imageLoader.enqueue(request) // Preload each image
        }
    }
}

@Composable
fun AnimatedImageSequence2(
    imageUrls: List<String>,
    frameDurationMillis: Long = 100L
) {

    // Preload images before starting the animation
    preloadImages(imageUrls)

    val currentFrameIndex = remember { mutableStateOf(0) }

    // Coroutine to update frame without triggering full recompositions
    LaunchedEffect(Unit) {
        while (isActive) {
            delay(frameDurationMillis)
            currentFrameIndex.value = (currentFrameIndex.value + 1) % imageUrls.size
        }
    }

    // Use SubcomposeLayout to prevent recomposing the entire layout
    SubcomposeLayout { constraints ->
        val imageContent = subcompose(Unit) {
            Box(
                modifier = Modifier
                    .aspectRatio(1280f / 720f),
                contentAlignment = Alignment.Center
            ) {
                CustomImageLoader(
                    url = imageUrls[currentFrameIndex.value],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(400.dp),
                    imageModifier = Modifier
                        .aspectRatio(1280f / 720f)
                        .fillMaxWidth(),
                )
            }
        }.first().measure(constraints)

        layout(imageContent.width, imageContent.height) {
            imageContent.place(0, 0)
        }
    }
}

@Composable
fun AnimatedImageSequence3(
    imageUrls: List<String>,
    frameDurationMillis: Long = 100L
) {
    val currentFrameIndex = remember { mutableStateOf(0) }

    // Coroutine to update frame without triggering full recompositions
    LaunchedEffect(Unit) {
        delay(frameDurationMillis) // Initial delay before starting the loop
        while (isActive) {
            currentFrameIndex.value = (currentFrameIndex.value + 1) % imageUrls.size
            delay(frameDurationMillis) // Frame duration for subsequent frames
        }
    }

    // Use SubcomposeLayout to prevent recomposing the entire layout
    SubcomposeLayout { constraints ->
        val imageContent = subcompose(Unit) {
            Box(
                modifier = Modifier
                    .aspectRatio(1280f / 720f)
                    .background(Color.Blue),
                contentAlignment = Alignment.Center
            ) {
                CustomImageLoader(
                    url = imageUrls[currentFrameIndex.value],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(400.dp),
                    imageModifier = Modifier
                        .aspectRatio(1280f / 720f)
                        .fillMaxWidth(),
                )
            }
        }.first().measure(constraints)

        layout(imageContent.width, imageContent.height) {
            imageContent.place(0, 0)
        }
    }
}


@Composable
fun AnimatedImageSequence4(
    imageUrls: List<String>,
    frameDurationMillis: Long = 100L
) {
    // Preload images before starting the animation
//    preloadImages(imageUrls)

    val currentFrameIndex = remember { mutableStateOf(0) }

    // Coroutine to update frame without triggering full recompositions
    LaunchedEffect(Unit) {
        while (isActive) {
            delay(frameDurationMillis)
            currentFrameIndex.value = (currentFrameIndex.value + 1) % imageUrls.size
        }
    }

    // Use SubcomposeLayout to prevent recomposing the entire layout
    SubcomposeLayout { constraints ->
        val imageContent = subcompose(Unit) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Crossfade(
                    targetState = imageUrls[currentFrameIndex.value],
                    label = ""
                ) { targetImageUrl ->
                    CustomImageLoader(
                        url = targetImageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(400.dp),
                        imageModifier = Modifier
                            .aspectRatio(1280f / 720f)
                            .fillMaxWidth(),
                    )
                }
            }
        }.first().measure(constraints)

        layout(imageContent.width, imageContent.height) {
            imageContent.place(0, 0)
        }
    }
}


@Composable
fun AnimatedImageSequence5(
    modifier: Modifier = Modifier,
    imageContainerModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    imageUrls: List<String>,
    frameDurationMillis: Long = 50L
) {

    val currentFrameIndex = remember { mutableIntStateOf(0) }
    val offsetX = remember { Animatable(0f) }

    // Coroutine to update frame without triggering full recompositions
    LaunchedEffect(Unit) {
        while (isActive) {
            // Animate the offset to create a sliding effect
            offsetX.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 10)
            ) // Slide out
            currentFrameIndex.intValue = (currentFrameIndex.intValue + 1) % imageUrls.size
            offsetX.snapTo(0f) // Reset position for the new frame
            delay(frameDurationMillis) // Control frame rate
        }
    }

    SubcomposeLayout { constraints ->
        val imageContent = subcompose(Unit) {
            Box(
                modifier = modifier
                    .aspectRatio(1280f / 720f),
                contentAlignment = Alignment.Center
            ) {
                imageUrls.forEachIndexed { index, imageUrl ->
                    val isVisible = index == currentFrameIndex.intValue
                    val offset = if (isVisible) 0f else -400f
                    CustomImageLoader(
                        url = imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = imageContainerModifier
                            .offset(x = offset.dp),
//                            .size(400.dp),
//                            .fillMaxSize(),
                        imageModifier = imageModifier
                            .aspectRatio(1280f / 720f)
                    )
                }
            }
        }.first().measure(constraints)

        layout(imageContent.width, imageContent.height) {
            imageContent.place(0, 0)
        }
    }
}


@Composable
fun AnimatedImageSequence6(
    imageUrls: List<String>,
    frameDurationMillis: Long = 40L
) {

    val currentFrameIndex = remember { mutableIntStateOf(0) }
    val offsetX = remember { Animatable(0f) }

    // Coroutine to update frame without triggering full recompositions
    LaunchedEffect(Unit) {
        while (isActive) {
            // Animate the offset to create a sliding effect
            offsetX.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 10)
            ) // Slide out
            currentFrameIndex.intValue = (currentFrameIndex.intValue + 1) % imageUrls.size
            offsetX.snapTo(0f) // Reset position for the new frame
            delay(frameDurationMillis) // Control frame rate
        }
    }

    // Use SubcomposeLayout to prevent recomposing the entire layout
    SubcomposeLayout { constraints ->
        val imageContent = subcompose(Unit) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Display the current frame with sliding transition
                imageUrls.forEachIndexed { index, imageUrl ->
                    val isVisible = index == currentFrameIndex.intValue
                    val offset =
                        if (isVisible) 0f else -400f // Adjust the offset based on visibility

                    Box(
                        modifier = Modifier
                            .offset(x = offset.dp) // Apply the offset
                            .size(400.dp)
                    ) {
                        CustomImageLoader(
                            url = imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier,
                            imageModifier = Modifier
                                .aspectRatio(1280f / 720f)
                                .fillMaxWidth(),
                        )
                    }
                }
            }
        }.first().measure(constraints)

        layout(imageContent.width, imageContent.height) {
            imageContent.place(0, 0)
        }
    }
}
