package io.github.aungkothet.padc.assignment11.network.dataAgents

import io.github.aungkothet.padc.assignment11.data.vos.PhotoVO
import io.github.aungkothet.padc.assignment11.data.vos.SearchResponse
import io.github.aungkothet.padc.assignment11.network.PhotoApi
import io.github.aungkothet.padc.assignment11.utils.ACCESS_KEY
import io.github.aungkothet.padc.assignment11.utils.BASE_URL
import io.github.aungkothet.padc.assignment11.utils.EM_NULL_RESPONSE
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit

object RetrofitDataAgent: PhotoDataAgent {
  override fun searchPhoto(searchValue: String): Observable<List<PhotoVO>> {
    val sv = searchValue
    return photoApi.getSearchPhoto(searchValue,ACCESS_KEY)
      .switchMap{
        val data = it
        if (data == null){
          Observable.error(RuntimeException(EM_NULL_RESPONSE))
        }else {
          Observable.just(data.results)
        }
      }
  }

  private val photoApi: PhotoApi

  init {
    val okHttpClient = OkHttpClient.Builder()
      .connectTimeout(15, TimeUnit.SECONDS)
      .readTimeout(60, TimeUnit.SECONDS)
      .writeTimeout(60, TimeUnit.SECONDS)
      .build()
    val retrofit = Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(okHttpClient)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    photoApi = retrofit.create(PhotoApi::class.java)
  }

//  override fun getPhotos(onSuccess: (List<PhotoVO>) -> Unit, onFailure: (String) -> Unit) {
//
//    val call = photoApi.getAllPhotos(ACCESS_KEY)
//    call.enqueue(object: Callback<List<PhotoVO>>{
//      override fun onFailure(call: Call<List<PhotoVO>>, t: Throwable) {
//        onFailure(t.localizedMessage)
//      }
//
//      override fun onResponse(call: Call<List<PhotoVO>>, response: Response<List<PhotoVO>>) {
//        val response = response.body()
//        if (response != null){
//          onSuccess(response)
//        }else{
//          onFailure(EM_NULL_RESPONSE)
//        }
//      }
//    })
//  }

  override fun getPhotosObservable(): Observable<List<PhotoVO>> {
    return photoApi.getAllPhotosObservable(ACCESS_KEY)
      .flatMap<List<PhotoVO>>{
        val data = it
        if (data == null){
          Observable.error(RuntimeException(EM_NULL_RESPONSE))
        }else {
          Observable.just(data)
        }
      }
  }

  override fun getPhotoDetail(id: String, onSuccess: (PhotoVO) -> Unit, onFailure: (String) -> Unit) {
    val call = photoApi.getPhotoDetail(id,ACCESS_KEY)
    call.enqueue(object: Callback<PhotoVO>{
      override fun onFailure(call: Call<PhotoVO>, t: Throwable) {
        onFailure(t.localizedMessage)
      }

      override fun onResponse(call: Call<PhotoVO>, response: Response<PhotoVO>) {
        val photoDetail = response.body()
        if (photoDetail != null){
          onSuccess(photoDetail)
        }else{
          onFailure(EM_NULL_RESPONSE)
        }
      }

    })
  }

  /*override fun getSearchPhoto(searchValue: String) {
    val call = photoApi.getSearchPhoto(searchValue)
    call.enqueue(object: Callback<GetSearchPhotosResponse>{
      override fun onFailure(call: Call<GetSearchPhotosResponse>, t: Throwable) {
        t.localizedMessage
      }

      override fun onResponse(call: Call<GetSearchPhotosResponse>, response: Response<GetSearchPhotosResponse>) {
        val searchPhoto = response.body()
        if (searchPhoto != null){
          if (searchPhoto.results != null){
            searchPhoto.results
          }
        }else {

        }
      }
    })
  }*/
}