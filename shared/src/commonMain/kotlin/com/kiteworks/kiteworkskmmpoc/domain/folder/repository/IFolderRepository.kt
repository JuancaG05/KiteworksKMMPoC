package com.kiteworks.kiteworkskmmpoc.domain.folder.repository

import com.kiteworks.kiteworkskmmpoc.domain.folder.Folder
import kotlinx.coroutines.flow.Flow

interface IFolderRepository {
    fun getAllFolders(): Flow<List<Folder>>
    suspend fun refreshFolders(accessToken: String)
}
