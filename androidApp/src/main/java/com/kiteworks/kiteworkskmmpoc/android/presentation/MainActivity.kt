package com.kiteworks.kiteworkskmmpoc.android.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kiteworks.kiteworkskmmpoc.android.presentation.folder.FolderListScreen
import com.kiteworks.kiteworkskmmpoc.android.presentation.login.LoginScreen
import com.kiteworks.kiteworkskmmpoc.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by lazy {
        getViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(loginViewModel) { openBrowser(it) }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            val authorizationCode = it.data?.getQueryParameter("code")
            if (authorizationCode != null) {
                loginViewModel.getAccessToken(
                    authorizationCode,
                    Uri.Builder()
                        .scheme(loginViewModel.redirectUriScheme)
                        .authority(loginViewModel.redirectUriHost)
                        .path(loginViewModel.redirectUriPath)
                        .build().toString()
                    )
            } else {
                val error = it.data?.getQueryParameter("error")
                Toast.makeText(baseContext, "ERROR: $error", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun openBrowser(uri: Uri) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        customTabsIntent.launchUrl(baseContext, uri)
    }
}

@Composable
fun AppNavigation(
    loginViewModel: LoginViewModel,
    onClickGoButton: (Uri) -> Unit,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, onClickGoButton, loginViewModel) }
        composable(
            "folderList/{accessToken}?",
            arguments = listOf(navArgument("accessToken") { nullable = true })
        ) { backStackEntry ->
            val accessToken = backStackEntry.arguments?.getString("accessToken")
            FolderListScreen(accessToken = accessToken, navController = navController) }
    }
}
