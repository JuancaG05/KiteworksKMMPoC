package com.kiteworks.kiteworkskmmpoc.data.login.datasources

import com.kiteworks.kiteworkskmmpoc.data.login.entities.AccessTokenRemoteEntity
import com.kiteworks.kiteworkskmmpoc.data.login.network.GetAccessTokenNetworkOperation
import com.kiteworks.kiteworkskmmpoc.domain.login.AccessToken

class LoginRemoteDataSource: ILoginRemoteDataSource {

    override suspend fun getAccessToken(
        serverUrl: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        authorizationCode: String
    ): AccessToken =
        GetAccessTokenNetworkOperation().execute(
            serverUrl = serverUrl,
            clientId = clientId,
            clientSecret = clientSecret,
            redirectUri = redirectUri,
            authorizationCode = authorizationCode,
        ).toModel()

    fun AccessTokenRemoteEntity.toModel(): AccessToken =
        AccessToken(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
}
