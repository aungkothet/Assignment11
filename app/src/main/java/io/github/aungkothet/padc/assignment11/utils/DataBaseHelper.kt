package io.github.aungkothet.padc.assignment11.utils

import android.content.Context
import io.github.aungkothet.padc.assignment11.persistance.PhotoDatabase

object DataBaseHelper {
    lateinit var photoDatabase: PhotoDatabase

    fun initDatabase(context: Context) {
        photoDatabase = PhotoDatabase.getInstance(context)
    }
}