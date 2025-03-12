package com.kiteworks.kiteworkskmmpoc.data.folder.repository

import com.kiteworks.kiteworkskmmpoc.data.folder.datasources.IFolderLocalDataSource
import com.kiteworks.kiteworkskmmpoc.data.folder.datasources.IFolderRemoteDataSource
import com.kiteworks.kiteworkskmmpoc.testutil.FOLDER
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mock
import org.kodein.mock.generated.injectMocks
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.Test
import kotlin.test.assertEquals

class FolderRepositoryTest: TestsWithMocks() {
    override fun setUpMocks() = mocker.injectMocks(this)

    @Mock
    lateinit var folderLocalDataSource: IFolderLocalDataSource
    @Mock
    lateinit var folderRemoteDataSource: IFolderRemoteDataSource

    private val folderRepository by withMocks { FolderRepository(folderLocalDataSource, folderRemoteDataSource) }


    @Test
    fun `getAllFolders returns a Flow with a list of Folder`() = runTest {
        every {
            folderLocalDataSource.getAllFolders()
        } returns flowOf(listOf(FOLDER))

        val listOfFolders = folderRepository.getAllFolders().first()
        assertEquals(listOf(FOLDER),listOfFolders)

        verify {
            folderLocalDataSource.getAllFolders()
        }
    }

    @Test
    fun `refreshAllFolders refreshes all folders that are in the root correctly`() = runTest {
        everySuspending {
            folderRemoteDataSource.refreshFolders()
        } returns listOf(FOLDER)

        every {
            folderLocalDataSource.insertFolder(FOLDER)
        } returns Unit

        folderRepository.refreshFolders()

        verifyWithSuspend {
            folderRemoteDataSource.refreshFolders()
            folderLocalDataSource.insertFolder(FOLDER)
        }

    }
}
