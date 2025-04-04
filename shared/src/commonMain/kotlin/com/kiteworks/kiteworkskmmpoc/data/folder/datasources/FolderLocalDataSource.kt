package com.kiteworks.kiteworkskmmpoc.data.folder.datasources

import app.cash.sqldelight.coroutines.asFlow
import com.kiteworks.kiteworkskmmpoc.db.FolderLocalEntity
import com.kiteworks.kiteworkskmmpoc.db.KiteworksKMMPoCDB
import com.kiteworks.kiteworkskmmpoc.domain.folder.Folder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FolderLocalDataSource(database: KiteworksKMMPoCDB): IFolderLocalDataSource {

    private val queries = database.folderQueries

    override fun getAllFolders(): Flow<List<Folder>> =
        queries.getAllFolders().asFlow().map { it.executeAsList().map { a -> a.toModel() }}

    override fun insertFolder(folder: Folder) {
        queries.insertFolder(
            id = folder.id,
            name = folder.name,
            userId = folder.userId,
            isShared = if (folder.isShared) 1L else 0L,
            path = folder.path,
            parentId = folder.parentId,
            type = folder.type,
            permalink = folder.permalink,
            created = folder.created,
            modified = folder.modified
        )
    }

    fun FolderLocalEntity.toModel(): Folder =
        Folder(
            id = id,
            name = name,
            userId = userId,
            isShared = isShared == 1L,
            path = path,
            parentId = parentId,
            type = type,
            permalink = permalink,
            created = created,
            modified = modified,
        )
}
