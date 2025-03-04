package com.kiteworks.kiteworkskmmpoc.data.folder.datasources

import com.kiteworks.kiteworkskmmpoc.data.folder.entities.FolderRemoteEntity
import com.kiteworks.kiteworkskmmpoc.data.folder.entities.FolderResponseRemoteEntity
import com.kiteworks.kiteworkskmmpoc.data.folder.network.RefreshFoldersNetworkOperation
import com.kiteworks.kiteworkskmmpoc.domain.folder.Folder

class FolderRemoteDataSource: IFolderRemoteDataSource {

    override suspend fun refreshFolders(): List<Folder> =
        RefreshFoldersNetworkOperation().execute().toModel()

    fun FolderResponseRemoteEntity.toModel(): List<Folder> {
        val folders = mutableListOf<Folder>()
        data.forEach { folders.add(it.toModel()) }
        return folders
    }

    fun FolderRemoteEntity.toModel(): Folder =
        Folder(
            id = id,
            name = name,
            userId = userId,
            isShared = isShared,
            path = path,
            parentId = parentId,
            type = type,
            permalink = permalink,
            created = created,
            modified = modified,
        )
}
