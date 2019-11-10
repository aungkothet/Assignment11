package io.github.aungkothet.padc.assignment11.network

import io.github.aungkothet.padc.assignment11.data.vos.PhotoVO
import io.github.aungkothet.padc.assignment11.data.vos.SearchResponse
import io.github.aungkothet.padc.assignment11.network.responses.GetSearchPhotosResponse
import io.github.aungkothet.padc.assignment11.utils.GET_PHOTO_DETAIL
import io.github.aungkothet.padc.assignment11.utils.GET_PHOTO_LIST
import io.github.aungkothet.padc.assignment11.utils.GET_SEARCH_PHOTO
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoApi {

    @GET(GET_PHOTO_LIST)
    fun getAllPhotosObservable(@Query("client_id") accessKey: String): Observable<List<PhotoVO>>

    @GET(GET_PHOTO_DETAIL)
    fun getPhotoDetail(@Path("id") id: String,@Query("client_id") accessKey: String): Call<PhotoVO>

    @GET(GET_SEARCH_PHOTO)
    fun getSearchPhoto(@Query("query") keyword: String,@Query("client_id") accessKey: String): Observable<SearchResponse>
}