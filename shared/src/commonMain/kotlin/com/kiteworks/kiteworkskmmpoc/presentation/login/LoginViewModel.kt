package com.kiteworks.kiteworkskmmpoc.presentation.login

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
    val clientId = ""
    private val clientSecret = ""

    val redirectUriScheme = "oc"
    val redirectUriHost = "android.owncloud.com"
    val redirectUriPath = "/"

    fun getAccessToken(authorizationCode: String, redirectUri: String) {
        viewModelScope.launch {
            val result = getAccessTokenUseCase.execute(
                clientId = clientId,
                clientSecret = clientSecret,
                redirectUri = redirectUri,
                authorizationCode = authorizationCode,
            )
            _accessTokenFlow.update { result }
        }
    }
}
