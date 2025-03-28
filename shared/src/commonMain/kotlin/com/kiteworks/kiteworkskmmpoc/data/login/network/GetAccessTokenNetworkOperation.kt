package com.kiteworks.kiteworkskmmpoc.data.login.network

import com.kiteworks.kiteworkskmmpoc.data.KtorClient.Companion.getClient
import com.kiteworks.kiteworkskmmpoc.data.login.entities.AccessTokenRemoteEntity
import io.ktor.client.call.body
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Parameters

class GetAccessTokenNetworkOperation {

    suspend fun execute(
        serverUrl: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        authorizationCode: String
    ): AccessTokenRemoteEntity {
        try {
            val result = getClient(serverUrl).post(urlString = "/oauth/token") {
                setBody(FormDataContent(Parameters.build {
                    append("client_id", clientId)
                    append("grant_type", "authorization_code")
                    append("client_secret", clientSecret)
                    append("redirect_uri", redirectUri)
                    append("code", authorizationCode)
                }))
            }.body<AccessTokenRemoteEntity>()
            return result
        } catch (e: Exception) {
            throw e
        }

    }
}
