package com.kiteworks.kiteworkskmmpoc

import com.kiteworks.kiteworkskmmpoc.data.folder.entities.FolderResponseRemoteEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class KtorClient {
    fun getClient(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(json = Json {
                    ignoreUnknownKeys = true
                })
            }

            install(DefaultRequest) {
                url {
                    host = "mobile.kiteworks.com"
                    protocol = URLProtocol.HTTPS
                    headers {
                        append("X-Accellion-Version", "28")
                    }
                    contentType(ContentType.Application.Json)
                }
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens("ivvFZHagsFq8fyYsH9JDLktYGGfN1sIZmyaaMZhi", "ivvFZHagsFq8fyYsH9JDLktYGGfN1sIZmyaaMZhi")
                    }
                }
            }

        }
    }

    suspend fun getFolders(): FolderResponseRemoteEntity {
        try {
            val result = getClient().get(urlString = "/rest/folders/top").body<FolderResponseRemoteEntity>()
            return result
        } catch (e: Exception) {
            throw e
        }
    }

}
