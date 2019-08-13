package com.lite.app

import com.lite.app.response.Article

interface IPresenter{

    fun onSuccess(articles : ArrayList<Article>?)

    fun onFailure()
}