package com.kiteworks.kiteworkskmmpoc.android.presentation.login

import android.net.Uri
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    // TODO: Write in-situ your client_id and client_secret, but DON'T PUSH IT TO THE REPO
    private val clientId = "34324e29-bc89-5aff-ac2f-648fc4ff98ce"
    private val clientSecret = ""

    private val redirectUriScheme = "oc"
    private val redirectUriHost = "android.owncloud.com"
    private val redirectUriPath = "/"

    fun buildAuthorizationRequest(serverUrl: String): Uri =
        Uri.parse("https://$serverUrl/oauth/authorize").buildUpon().apply {
            appendQueryParameter("client_id", clientId)
            appendQueryParameter("redirect_uri", Uri.Builder().scheme(redirectUriScheme).authority(redirectUriHost).path(redirectUriPath).build().toString())
            appendQueryParameter("response_type", "code")
            appendQueryParameter("scope", "")
            appendQueryParameter("m", "1")
        }.build()
}
