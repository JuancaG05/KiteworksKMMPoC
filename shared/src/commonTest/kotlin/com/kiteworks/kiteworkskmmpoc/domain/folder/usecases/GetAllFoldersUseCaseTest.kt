package com.kiteworks.kiteworkskmmpoc.domain.folder.usecases

import com.kiteworks.kiteworkskmmpoc.domain.folder.repository.IFolderRepository
import com.kiteworks.kiteworkskmmpoc.testUtil.FOLDER
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mock
import org.kodein.mock.generated.injectMocks
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.Test
import kotlin.test.assertEquals

class GetAllFoldersUseCaseTest: TestsWithMocks() {
    override fun setUpMocks() = mocker.injectMocks(this)

    @Mock
    lateinit var folderRepository: IFolderRepository

    private val getAllFoldersUseCase by withMocks { GetAllFoldersUseCase(folderRepository) }

    @Test
    fun `execute returns a Flow with a list of Folder`() = runTest {
        every {
            folderRepository.getAllFolders()
        } returns flowOf(listOf(FOLDER))

        val listOfFolders = getAllFoldersUseCase.execute().first()
        assertEquals(listOf(FOLDER), listOfFolders)

        verify {
            folderRepository.getAllFolders()
        }
    }

}
