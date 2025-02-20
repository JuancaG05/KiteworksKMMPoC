package com.kiteworks.kiteworkskmmpoc.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kiteworks.kiteworkskmmpoc.KtorClient
import com.kiteworks.kiteworkskmmpoc.android.viewModel.MainViewModel
import com.kiteworks.kiteworkskmmpoc.model.FolderResponse

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Preview
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginView(navController) }
        composable("fileList") { FileListView(navController) }
    }
}

@Composable
fun LoginView(navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        KiteworksLogo()
        HeaderField()
        ServerField()
        GoButton(navController)
        PoCTag()
    }
}

@Preview
@Composable
fun HeaderField() {
    Text(
        modifier = Modifier
            .padding(start = 10.dp),
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        color = Color(0xFF333333),
        text = stringResource(R.string.login_header),
    )
}

@Preview
@Composable
fun ServerField() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .width(360.dp),
        value = text,
        onValueChange = { text = it },
        label = { Text(stringResource(R.string.server_url_hint)) },
    )
}

@Composable
fun GoButton(navController: NavController) {
    Button(
        modifier = Modifier
            .width(360.dp),
        onClick = { navController.navigate("fileList")},
        colors = ButtonDefaults.buttonColors(containerColor = Color(66, 92, 199)),
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(
            text = stringResource(R.string.go_button_label)
        )
    }
}

@Preview
@Composable
fun KiteworksLogo() {
    Image(
        modifier = Modifier
            .padding(bottom = 10.dp, top = 50.dp)
            .width(360.dp),
        painter = painterResource(R.drawable.kiteworks_logo),
        contentDescription = stringResource(R.string.kiteworks_logo_content_description),
    )
}

@Preview
@Composable
fun PoCTag() {
    Text(
        modifier = Modifier
            .padding(top = 425.dp),
        text = stringResource(R.string.poc_tag)
    )
}


@Composable
fun FileListView(navController: NavController) {
    val viewModel = MainViewModel(KtorClient())
    var folders by remember { mutableStateOf<FolderResponse?>(null) }
    LaunchedEffect(Unit) {
        folders = viewModel.getFolders()
    }

    folders?.data?.let { folderList ->
        LazyColumn {
            items(folderList) { folder ->
                Text(folder.name)
            }
        }
    }
}
