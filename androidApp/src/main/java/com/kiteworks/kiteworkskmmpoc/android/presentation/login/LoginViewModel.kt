package com.kiteworks.kiteworkskmmpoc.android.presentation.login

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiteworks.kiteworkskmmpoc.domain.login.AccessToken
import com.kiteworks.kiteworkskmmpoc.domain.login.usecases.GetAccessTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
): ViewModel() {

    private val _accessTokenFlow: MutableStateFlow<AccessToken?> = MutableStateFlow(null)
    val accessTokenFlow: StateFlow<AccessToken?> = _accessTokenFlow

    // TODO: Write in-situ your client_id and client_secret, but DON'T PUSH IT TO THE REPO
    private val clientId = ""
    private val clientSecret = ""

    private val redirectUriScheme = "oc"
    private val redirectUriHost = "android.owncloud.com"
    private val redirectUriPath = "/"

    fun buildAuthorizationRequest(serverUrl: String): Uri =
        Uri.parse("https://$serverUrl/oauth/authorize").buildUpon().apply {
            appendQueryParameter("client_id", clientId)
            appendQueryParameter("redirect_uri", buildRedirectUri().toString())
            appendQueryParameter("response_type", "code")
            appendQueryParameter("scope", "")
            appendQueryParameter("m", "1")
        }.build()

    fun getAccessToken(authorizationCode: String) {
        viewModelScope.launch {
            val result = getAccessTokenUseCase.execute(
                clientId = clientId,
                clientSecret = clientSecret,
                redirectUri = buildRedirectUri().toString(),
                authorizationCode = authorizationCode,
            )
            _accessTokenFlow.update { result }
        }
    }

    private fun buildRedirectUri(): Uri =
        Uri.Builder().scheme(redirectUriScheme).authority(redirectUriHost).path(redirectUriPath).build()
}
