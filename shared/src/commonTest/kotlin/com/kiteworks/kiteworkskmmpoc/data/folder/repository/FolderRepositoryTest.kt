package com.kiteworks.kiteworkskmmpoc.data.folder.repository

import com.kiteworks.kiteworkskmmpoc.data.folder.datasources.IFolderLocalDataSource
import com.kiteworks.kiteworkskmmpoc.data.folder.datasources.IFolderRemoteDataSource
import com.kiteworks.kiteworkskmmpoc.testUtil.FOLDER
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import dev.mokkery.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FolderRepositoryTest {

    private val folderLocalDataSource = mock<IFolderLocalDataSource>()
    private val folderRemoteDataSource = mock<IFolderRemoteDataSource>()

    private val folderRepository = FolderRepository(folderLocalDataSource, folderRemoteDataSource)

    @Test
    fun `getAllFolders returns a Flow with a list of Folder`()= runTest {
        every {
            folderLocalDataSource.getAllFolders()
        } returns flowOf(listOf(FOLDER))

        val listOfFolders = folderRepository.getAllFolders().first()
        assertEquals(listOf(FOLDER), listOfFolders)

        verify {
            folderLocalDataSource.getAllFolders()
        }

    }
}
