package com.kiteworks.kiteworkskmmpoc.data.login.repository

import com.kiteworks.kiteworkskmmpoc.data.login.datasources.ILoginRemoteDataSource
import com.kiteworks.kiteworkskmmpoc.domain.login.AccessToken
import com.kiteworks.kiteworkskmmpoc.domain.login.repository.ILoginRepository

class LoginRepository(
    private val loginRemoteDataSource: ILoginRemoteDataSource,
): ILoginRepository {

    override suspend fun getAccessToken(
        serverUrl: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        authorizationCode: String
    ): AccessToken =
        loginRemoteDataSource.getAccessToken(
            serverUrl = serverUrl,
            clientId = clientId,
            clientSecret = clientSecret,
            redirectUri = redirectUri,
            authorizationCode = authorizationCode
        )
}
