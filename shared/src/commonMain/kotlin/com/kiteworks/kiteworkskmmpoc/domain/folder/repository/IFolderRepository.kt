package com.kiteworks.kiteworkskmmpoc.domain.folder.repository

import com.kiteworks.kiteworkskmmpoc.domain.folder.Folder

interface IFolderRepository {
    suspend fun getAllFolders(): List<Folder>
}
