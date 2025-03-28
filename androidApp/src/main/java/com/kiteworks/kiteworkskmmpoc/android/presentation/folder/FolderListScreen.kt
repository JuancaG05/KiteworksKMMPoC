package com.kiteworks.kiteworkskmmpoc.android.presentation.folder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kiteworks.kiteworkskmmpoc.android.R
import com.kiteworks.kiteworkskmmpoc.domain.folder.Folder
import com.kiteworks.kiteworkskmmpoc.presentation.folder.FolderListViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun FolderListScreen(
    serverUrl: String,
    accessToken: String?,
    navController: NavController,
    viewModel: FolderListViewModel = koinViewModel()
) {
    val listOfFolders by viewModel.listOfFolders.collectAsState()

    LaunchedEffect(key1 = true) {
        accessToken?.let { viewModel.refreshFolders(serverUrl, it) }
    }

    Scaffold(
        topBar = { TopBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                shape = RoundedCornerShape(16.dp),
                containerColor = Color(0xFF425CC7)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.fab_content_description),
                    tint = Color.White
                )
            }
        }
    ) { paddingValues ->
        LazyColumn (
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            items(listOfFolders) { folder ->
                Folder(folder)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(66, 92, 199)),
        title = {
            Text(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                text = stringResource(R.string.top_bar_title),
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate("login") }) {
                Icon(
                    tint = Color.White,
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.arrow_back_content_description)
                )
            }
        },
    )
}

@Composable
fun Folder(folderInfo: Folder) {
    Row (
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp),
    ) {
        Image(
            modifier = Modifier
                .size(25.dp),
            painter = if (folderInfo.isShared) {
                painterResource(R.drawable.ic_shared_folder)
            } else {
                painterResource(R.drawable.ic_folder)
            },
            contentDescription = stringResource(R.string.folder_icon_content_description)
        )
        Column (
            modifier = Modifier
                .padding(start = 10.dp, bottom = 10.dp)
        ) {
            Text(
                text = folderInfo.name,
                color = Color.Black,
                fontSize = 16.sp
            )
            Text(
                text = "updated ${formatDateTime(folderInfo.modified)}",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
    CustomDivider()
}

@Composable
fun CustomDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color.Transparent, Color.Gray, Color.Transparent)
                ),
            )
    )
}

private fun formatDateTime(dateTime: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH)
    val outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a", Locale.ENGLISH)
    val dateTime = OffsetDateTime.parse(dateTime, inputFormatter)
    return dateTime.format(outputFormatter)
}
