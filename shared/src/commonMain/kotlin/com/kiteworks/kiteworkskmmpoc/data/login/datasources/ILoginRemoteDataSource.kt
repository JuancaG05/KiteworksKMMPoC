package com.kiteworks.kiteworkskmmpoc.data.login.datasources

import com.kiteworks.kiteworkskmmpoc.domain.login.AccessToken

interface ILoginRemoteDataSource {
    suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        authorizationCode: String
    ): AccessToken
}
