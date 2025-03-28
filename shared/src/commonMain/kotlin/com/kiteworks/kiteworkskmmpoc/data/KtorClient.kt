package com.kiteworks.kiteworkskmmpoc.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient {

    companion object {
        fun getClient(hostUrl: String, accessToken: String? = null): HttpClient {
            return HttpClient {
                install(ContentNegotiation) {
                    json(json = Json {
                        ignoreUnknownKeys = true
                    })
                }

                install(DefaultRequest) {
                    url {
                        host = hostUrl
                        protocol = URLProtocol.HTTPS
                        header("X-Accellion-Version", "28")
                        contentType(ContentType.Application.Json)
                    }
                }

                if (accessToken != null) {
                    install(Auth) {
                        bearer {
                            loadTokens {
                                BearerTokens(accessToken, accessToken)
                            }
                        }
                    }
                }

                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            co.touchlab.kermit.Logger.d(message)
                        }
                    }
                    level = LogLevel.ALL
                }
            }
        }
    }
}
