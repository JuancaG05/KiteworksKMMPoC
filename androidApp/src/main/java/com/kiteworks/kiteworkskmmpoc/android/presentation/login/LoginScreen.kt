package com.kiteworks.kiteworkskmmpoc.android.presentation.login

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kiteworks.kiteworkskmmpoc.android.R

@Composable
fun LoginScreen(
    navController: NavController,
    onClickGoButton: (Uri) -> Unit,
    viewModel: LoginViewModel,
) {
    var serverUrl by remember { mutableStateOf("") }
    val accessToken by viewModel.accessTokenFlow.collectAsState()

    LaunchedEffect(accessToken) {
        if (accessToken != null) {
            val accessTokenString = accessToken?.accessToken
            navController.navigate("folderList/$accessTokenString?")
        }
    }


    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        KiteworksLogo()
        HeaderField()
        ServerField(
            serverUrl,
            { serverUrl = it }
        )
        GoButton {
            val authorizationRequestUri = viewModel.buildAuthorizationRequest(serverUrl)
            onClickGoButton(authorizationRequestUri)
        }
    }
}

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

@Composable
fun ServerField(
    serverUrl: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .width(360.dp),
        value = serverUrl,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(R.string.server_url_hint)) },
    )
}

@Composable
fun GoButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .width(360.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(66, 92, 199)),
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(
            text = stringResource(R.string.go_button_label)
        )
    }
}

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
