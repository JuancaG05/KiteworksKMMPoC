package com.kiteworks.kiteworkskmmpoc.data.login.repository

import com.kiteworks.kiteworkskmmpoc.data.login.datasources.ILoginRemoteDataSource
import com.kiteworks.kiteworkskmmpoc.domain.login.AccessToken
import com.kiteworks.kiteworkskmmpoc.domain.login.repository.ILoginRepository

class LoginRepository(
    private val loginRemoteDataSource: ILoginRemoteDataSource,
): ILoginRepository {

    override suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        authorizationCode: String
    ): AccessToken =
        loginRemoteDataSource.getAccessToken(
            clientId = clientId,
            clientSecret = clientSecret,
            redirectUri = redirectUri,
            authorizationCode = authorizationCode
        )
}
