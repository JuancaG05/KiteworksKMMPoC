package com.kiteworks.kiteworkskmmpoc.dependencyinjection

import com.kiteworks.kiteworkskmmpoc.presentation.folder.FolderListViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            sharedCommonModule,
            sharedIOSModule,
        )
    }
}

class KoinHelper : KoinComponent {
    val folderListViewModel: FolderListViewModel by inject()
}
