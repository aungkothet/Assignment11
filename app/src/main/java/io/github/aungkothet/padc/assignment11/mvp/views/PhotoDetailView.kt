package io.github.aungkothet.padc.assignment11.mvp.views

import io.github.aungkothet.padc.assignment11.data.vos.PhotoVO
import io.github.aungkothet.padc.assignment11.mvp.views.BaseView

interface PhotoDetailView: BaseView {
    fun displayPhotoDetail(photoVO: PhotoVO)
    fun errorMessage(errorMessage: String)
}