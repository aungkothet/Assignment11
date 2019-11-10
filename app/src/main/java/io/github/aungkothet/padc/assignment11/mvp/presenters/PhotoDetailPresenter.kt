package io.github.aungkothet.padc.assignment11.mvp.presenters

import io.github.aungkothet.padc.assignment11.activities.BaseAcitvity
import io.github.aungkothet.padc.assignment11.data.models.PhotoListModelImpl
import io.github.aungkothet.padc.assignment11.mvp.views.PhotoDetailView

class PhotoDetailPresenter: BasePresenter<PhotoDetailView>() {

    fun onUiReady(activity: BaseAcitvity, photoId: String) {
        if (photoId != null) {
            val model = PhotoListModelImpl
            model.getPhotoDetail(photoId, {
                mView.displayPhotoDetail(it)
            }, {
                mView.errorMessage(it)
            })
        }
    }
}