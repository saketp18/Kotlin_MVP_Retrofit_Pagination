package com.lite.app.response

import com.google.gson.annotations.SerializedName

class ResponseCall {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("totalResults")
    var count: Int = 0

    @SerializedName("articles")
    var articles: ArrayList<Article>? = null
}