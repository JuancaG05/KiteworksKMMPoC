import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        KoinIOSKt.doInitKoin()
    }
    
	var body: some Scene {
        let folderListViewModel = KoinHelper().folderListViewModel
        
		WindowGroup {
            FolderListScreen(viewModel: folderListViewModel)
		}
	}
}
