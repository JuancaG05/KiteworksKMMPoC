package com.kiteworks.kiteworkskmmpoc.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.kiteworks.kiteworkskmmpoc.db.KiteworksKMMPoCDB

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(KiteworksKMMPoCDB.Schema, "kiteworkskmmpoc.db")
}
