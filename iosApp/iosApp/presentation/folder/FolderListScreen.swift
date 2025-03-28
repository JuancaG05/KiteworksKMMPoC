//
//  FolderListScreen.swift
//  iosApp
//
//  Created by Juan Carlos Garrote on 17/3/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct FolderListScreen: View {
    let viewModel: FolderListViewModel
    
    var body: some View {
        Observing(viewModel.listOfFolders) { listOfFolders in
            ListView(listOfFolders: listOfFolders)
        }
        .onAppear {
            viewModel.refreshFolders(serverUrl: "mobile.kiteworks.com", accessToken: "FwtOOPPh2fe2RwxbmYv3eatTZeyUZR7Co58bLFIt")
        }
    }
}

struct ListView: View {
    let listOfFolders: Array<Folder>
    
    var body: some View {
        List(listOfFolders, id: \.self) { folder in
            Text(folder.name)
        }
    }
}
