package com.kiteworks.kiteworkskmmpoc.data.folder.datasources

import com.kiteworks.kiteworkskmmpoc.domain.folder.Folder

interface IFolderLocalDataSource {
    suspend fun getAllFolders(): List<Folder>
}
