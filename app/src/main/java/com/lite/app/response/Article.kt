package com.lite.app.response

import com.google.gson.annotations.SerializedName

class Article {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("content")
    var content: String? = null
}