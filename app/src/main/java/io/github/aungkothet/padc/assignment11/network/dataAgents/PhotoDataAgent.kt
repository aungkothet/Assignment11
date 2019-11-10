package io.github.aungkothet.padc.assignment11.network.dataAgents

import io.github.aungkothet.padc.assignment11.data.vos.PhotoVO
import io.reactivex.Observable

interface PhotoDataAgent {

//  fun getPhotos(
//      onSuccess: (List<PhotoVO>) -> Unit,
//      onFailure: (String) -> Unit)

  fun getPhotosObservable(): Observable<List<PhotoVO>>

  fun getPhotoDetail(
      id: String,
      onSuccess: (PhotoVO) -> Unit,
      onFailure: (String) -> Unit
  )

  fun searchPhoto(searchValue: String): Observable<List<PhotoVO>>
}