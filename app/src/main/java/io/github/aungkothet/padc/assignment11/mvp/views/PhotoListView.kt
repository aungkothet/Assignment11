package io.github.aungkothet.padc.assignment11.mvp.views

import io.github.aungkothet.padc.assignment11.data.vos.PhotoVO
import io.github.aungkothet.padc.assignment11.mvp.views.BaseView

interface PhotoListView: BaseView {

    fun displayPhotoList(photoList: List<PhotoVO>)
    fun displayError(errorMessage: String)
    fun navigateToDetail(photoId: String)
}