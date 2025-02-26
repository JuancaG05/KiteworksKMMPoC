package com.kiteworks.kiteworkskmmpoc.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.kiteworks.kiteworkskmmpoc.db.KiteworksKMMPoCDB

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(KiteworksKMMPoCDB.Schema, context, "kiteworkskmmpoc.db")
}
