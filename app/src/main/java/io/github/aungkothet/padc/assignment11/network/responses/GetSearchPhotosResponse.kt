package io.github.aungkothet.padc.assignment11.network.responses

import com.google.gson.annotations.SerializedName
import io.github.aungkothet.padc.assignment11.data.vos.PhotoVO

data class GetSearchPhotosResponse(

    @SerializedName("results")
    var results: List<PhotoVO>
)