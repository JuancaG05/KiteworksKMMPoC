package com.kiteworks.kiteworkskmmpoc.data.folder.repository

import com.kiteworks.kiteworkskmmpoc.data.folder.datasources.IFolderLocalDataSource
import com.kiteworks.kiteworkskmmpoc.domain.folder.Folder
import com.kiteworks.kiteworkskmmpoc.domain.folder.repository.IFolderRepository

class FolderRepository(
    private val folderLocalDataSource: IFolderLocalDataSource
): IFolderRepository {

    override suspend fun getAllFolders(): List<Folder> =
        folderLocalDataSource.getAllFolders()
}
