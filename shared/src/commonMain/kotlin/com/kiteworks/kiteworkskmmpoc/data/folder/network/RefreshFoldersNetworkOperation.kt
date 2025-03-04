package com.kiteworks.kiteworkskmmpoc.data.folder.network

import com.kiteworks.kiteworkskmmpoc.data.KtorClient.Companion.getClient
import com.kiteworks.kiteworkskmmpoc.data.folder.entities.FolderResponseRemoteEntity
import io.ktor.client.call.body
import io.ktor.client.request.get

class RefreshFoldersNetworkOperation {

    suspend fun execute(): FolderResponseRemoteEntity {
        try {
            val result = getClient().get(urlString = "/rest/folders/top").body<FolderResponseRemoteEntity>()
            return result
        } catch (e: Exception) {
            throw e
        }
    }
}
