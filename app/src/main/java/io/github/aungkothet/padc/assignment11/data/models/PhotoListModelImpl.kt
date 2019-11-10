package io.github.aungkothet.padc.assignment11.data.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import io.github.aungkothet.padc.assignment11.data.vos.PhotoVO
import io.github.aungkothet.padc.assignment11.network.dataAgents.RetrofitDataAgent
import io.github.aungkothet.padc.assignment11.utils.EM_NULL_RESPONSE
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object PhotoListModelImpl : PhotoListModel, BaseModel() {
    override fun getSearchPhoto(searchValue: String): Observable<List<PhotoVO>>{
//        var searchResult = listOf<PhotoVO>()
       return  RetrofitDataAgent.searchPhoto(searchValue)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
               Log.e("AKTERR","onPhotoListModelImpl  ${it.localizedMessage}")
            }.doOnNext {
               it
           }
//            .subscribe({
//                Log.d("RoomRX", it.toString())
//                searchResult = it
//            }, {
//                Log.d("RoomRX", it.localizedMessage)
//            })
//        return searchResult
    }

    override fun getAllPhotos(onFailure: (String) -> Unit): LiveData<List<PhotoVO>> {

        val databaseSingle = dataBase.photoDao().getAllPhotosMaybe().subscribeOn(Schedulers.io())
            .flatMap {
                if (it.isEmpty()) {
                    Maybe.empty()
                } else {
                    Maybe.just(it)
                }
            }

        val networkObservable = RetrofitDataAgent.getPhotosObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                onFailure(it.message ?: EM_NULL_RESPONSE)
            }
            .doOnNext {
                Log.d("AllPhotos network", it.toString())
            }

        Observable.concat(databaseSingle.toObservable(), networkObservable)
            .firstElement()
            .flatMap<LongArray> {
                dataBase.photoDao().insertPhotos(it).subscribeOn(Schedulers.io()).toMaybe()
            }
            .subscribe()

        val photosFromDB = dataBase.photoDao().getAllPhotos()
        return Transformations.distinctUntilChanged(photosFromDB)
    }

    override fun getPhotoDetail(
        id: String,
        onSuccess: (PhotoVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        RetrofitDataAgent.getPhotoDetail(id,
            onSuccess = {
                onSuccess(it)
            },
            onFailure = {
                onFailure(it)
            })
    }

//    override fun getSearchPhoto(searchValue: String): List<PhotoVO> {
//        return database.photoDao().getSearchPhoto("%$searchValue%")
//    }
}