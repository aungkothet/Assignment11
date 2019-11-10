package io.github.aungkothet.padc.assignment11.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.aungkothet.padc.assignment11.data.models.PhotoListModel
import io.github.aungkothet.padc.assignment11.data.models.PhotoListModelImpl

abstract class BaseAcitvity: AppCompatActivity() {

    protected lateinit var model: PhotoListModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = PhotoListModelImpl
    }
}