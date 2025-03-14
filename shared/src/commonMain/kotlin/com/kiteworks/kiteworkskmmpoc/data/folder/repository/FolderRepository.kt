package com.kiteworks.kiteworkskmmpoc.data.folder.repository

import com.kiteworks.kiteworkskmmpoc.data.folder.datasources.IFolderLocalDataSource
import com.kiteworks.kiteworkskmmpoc.data.folder.datasources.IFolderRemoteDataSource
import com.kiteworks.kiteworkskmmpoc.domain.folder.Folder
import com.kiteworks.kiteworkskmmpoc.domain.folder.repository.IFolderRepository
import kotlinx.coroutines.flow.Flow

class FolderRepository(
    private val folderLocalDataSource: IFolderLocalDataSource,
    private val folderRemoteDataSource: IFolderRemoteDataSource,
): IFolderRepository {

    override fun getAllFolders(): Flow<List<Folder>> =
        folderLocalDataSource.getAllFolders()

    override suspend fun refreshFolders(accessToken: String) {
        folderRemoteDataSource.refreshFolders(accessToken).forEach { folder ->
            folderLocalDataSource.insertFolder(folder)
        }
    }
}
