import SwiftUI
import shared

@main
struct iOSApp: App {
    
	var body: some Scene {
        let sqlDriver = DatabaseDriverFactory().createDriver()
        let database = KiteworksKMMPoCDBCompanion().invoke(driver: sqlDriver)
        let folderLocalDataSource = FolderLocalDataSource(database: database)
        let folderRemoteDataSource = FolderRemoteDataSource()
        let folderRepository = FolderRepository(folderLocalDataSource: folderLocalDataSource, folderRemoteDataSource: folderRemoteDataSource)
        let refreshFoldersUseCase = RefreshFoldersUseCase(folderRepository: folderRepository)
        let getAllFoldersUseCase = GetAllFoldersUseCase(folderRepository: folderRepository)
        let folderListViewModel = FolderListViewModel(refreshFoldersUseCase: refreshFoldersUseCase, getAllFoldersUseCase: getAllFoldersUseCase)
		WindowGroup {
            FolderListScreen(viewModel: folderListViewModel)
		}
	}
}
