package io.github.aungkothet.padc.assignment11.data.models

import androidx.lifecycle.LiveData
import io.github.aungkothet.padc.assignment11.data.vos.PhotoVO
import io.reactivex.Observable

interface PhotoListModel {

    fun getAllPhotos(
        onFailure: (String) -> Unit
    ): LiveData<List<PhotoVO>>

    fun getPhotoDetail(
        id: String,
        onSuccess: (PhotoVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSearchPhoto(searchValue: String): Observable<List<PhotoVO>>
}