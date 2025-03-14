package com.kiteworks.kiteworkskmmpoc.data.folder.datasources

import com.kiteworks.kiteworkskmmpoc.domain.folder.Folder

interface IFolderRemoteDataSource {
    suspend fun refreshFolders(accessToken: String): List<Folder>
}
