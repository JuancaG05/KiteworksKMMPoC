package com.kiteworks.kiteworkskmmpoc.data.folder.datasources

import com.kiteworks.kiteworkskmmpoc.domain.folder.Folder
import kotlinx.coroutines.flow.Flow

interface IFolderLocalDataSource {
    fun getAllFolders(): Flow<List<Folder>>
    fun insertFolder(folder: Folder)
}
