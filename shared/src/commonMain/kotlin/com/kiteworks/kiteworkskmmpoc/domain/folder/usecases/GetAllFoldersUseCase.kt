package com.kiteworks.kiteworkskmmpoc.domain.folder.usecases

import com.kiteworks.kiteworkskmmpoc.domain.folder.Folder
import com.kiteworks.kiteworkskmmpoc.domain.folder.repository.IFolderRepository
import kotlinx.coroutines.flow.Flow

class GetAllFoldersUseCase(
    private val folderRepository: IFolderRepository
) {

    fun execute(): Flow<List<Folder>> =
        folderRepository.getAllFolders()

}
