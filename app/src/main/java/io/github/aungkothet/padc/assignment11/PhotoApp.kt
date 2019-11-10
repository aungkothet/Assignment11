package io.github.aungkothet.padc.assignment11

import android.app.Application
import io.github.aungkothet.padc.assignment11.data.models.PhotoListModelImpl
import io.github.aungkothet.padc.assignment11.utils.DataBaseHelper

class PhotoApp: Application(){

    override fun onCreate() {
        super.onCreate()
        DataBaseHelper.initDatabase(this)
    }
}