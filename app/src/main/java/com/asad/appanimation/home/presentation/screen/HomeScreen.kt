package com.asad.appanimation.home.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.asad.appanimation.home.presentation.FolderItem
import com.asad.appanimation.home.presentation.viewModel.HomeViewModel
import java.io.File

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onFolderClick: (File) -> Unit
) {
    val uiState = homeViewModel.uiState.collectAsStateWithLifecycle()
    val folders = uiState.value.folders ?: emptyList()

    HomeContent(folders = folders, onFolderClick = onFolderClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeContent(
    folders: List<File> = emptyList(),
    onFolderClick: (File) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Home")
            }
            )
        }
    ) { paddingValue ->
        Box(
            modifier = Modifier
                .padding(paddingValue)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(folders.size) { index ->
                    val folder = folders[index]
                    FolderItem(folder = folder, onClick = onFolderClick)
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeContentPreview() {

}