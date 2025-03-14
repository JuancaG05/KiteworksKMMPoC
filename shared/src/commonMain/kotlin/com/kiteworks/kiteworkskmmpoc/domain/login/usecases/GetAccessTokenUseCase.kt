package com.kiteworks.kiteworkskmmpoc.domain.login.usecases

import com.kiteworks.kiteworkskmmpoc.domain.login.AccessToken
import com.kiteworks.kiteworkskmmpoc.domain.login.repository.ILoginRepository

class GetAccessTokenUseCase(
    private val loginRepository: ILoginRepository
) {

    suspend fun execute(
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        authorizationCode: String
    ): AccessToken =
        loginRepository.getAccessToken(
            clientId = clientId,
            clientSecret = clientSecret,
            redirectUri = redirectUri,
            authorizationCode = authorizationCode
        )
}
