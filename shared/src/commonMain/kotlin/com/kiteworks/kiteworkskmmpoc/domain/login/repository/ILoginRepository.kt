package com.kiteworks.kiteworkskmmpoc.domain.login.repository

import com.kiteworks.kiteworkskmmpoc.domain.login.AccessToken

interface ILoginRepository {
    suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        authorizationCode: String
    ): AccessToken
}
