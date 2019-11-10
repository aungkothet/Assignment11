package io.github.aungkothet.padc.assignment11.data.vos

import com.google.gson.annotations.SerializedName

data class UnsplashUrlsVo(

    @SerializedName("raw")
    var raw: String?,

    @SerializedName("full")
    var full: String?,

    @SerializedName("regular")
    var regular: String?,

    @SerializedName("small")
    var small: String,

    @SerializedName("thumb")
    var thumb: String?,

    @SerializedName("medium")
    val medium: String?,

    @SerializedName("large")
    val large: String?
)