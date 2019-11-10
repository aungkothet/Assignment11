package io.github.aungkothet.padc.assignment11.mvp.presenters

import androidx.lifecycle.Observer
import io.github.aungkothet.padc.assignment11.activities.BaseAcitvity
import io.github.aungkothet.padc.assignment11.data.models.PhotoListModelImpl
import io.github.aungkothet.padc.assignment11.delegates.PhotoItemDelegate
import io.github.aungkothet.padc.assignment11.mvp.views.PhotoListView

class PhotoListPresenter : BasePresenter<PhotoListView>(), PhotoItemDelegate {

    override fun onItemClicked(id: String) {
        mView.navigateToDetail(id)
    }

    fun onUiReady(activity: BaseAcitvity) {
        val model = PhotoListModelImpl
        model.getAllPhotos { mView.displayError(it) }
            .observe(activity, Observer {
                mView.displayPhotoList(it)
            })
    }

    fun onSearchType(searchValue: String, activity: BaseAcitvity) {
        val photoList = PhotoListModelImpl.getSearchPhoto(searchValue).subscribe {
            mView.displayPhotoList(it)
        }
    }

    /*fun displaySearchPhotos(searchValue: String){
        val model = PhotoListModelImpl
        model.getSearchPhoto(searchValue)
    }*/
}