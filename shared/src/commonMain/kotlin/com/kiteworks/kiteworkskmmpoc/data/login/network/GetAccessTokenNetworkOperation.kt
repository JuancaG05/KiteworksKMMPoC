package com.kiteworks.kiteworkskmmpoc.data.login.network

import com.kiteworks.kiteworkskmmpoc.data.KtorClient.Companion.getClient
import com.kiteworks.kiteworkskmmpoc.data.login.entities.AccessTokenRemoteEntity
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post

class GetAccessTokenNetworkOperation {

    suspend fun execute(
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        authorizationCode: String
    ): AccessTokenRemoteEntity {
        try {
            val result = getClient().post(urlString = "/oauth/token") {
                parameter("client_id", clientId)
                parameter("grant_type", "authorization_code")
                parameter("client_secret", clientSecret)
                parameter("redirect_uri", redirectUri)
                parameter("code", authorizationCode)
            }
            return AccessTokenRemoteEntity("a", "a")
        } catch (e: Exception) {
            throw e
        }

    }
}
