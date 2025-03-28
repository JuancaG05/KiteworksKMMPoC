package com.kiteworks.kiteworkskmmpoc.domain.login.usecases

import com.kiteworks.kiteworkskmmpoc.domain.login.AccessToken
import com.kiteworks.kiteworkskmmpoc.domain.login.repository.ILoginRepository

class GetAccessTokenUseCase(
    private val loginRepository: ILoginRepository
) {

    suspend fun execute(
        serverUrl: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        authorizationCode: String
    ): AccessToken =
        loginRepository.getAccessToken(
            serverUrl = serverUrl,
            clientId = clientId,
            clientSecret = clientSecret,
            redirectUri = redirectUri,
            authorizationCode = authorizationCode
        )
}
