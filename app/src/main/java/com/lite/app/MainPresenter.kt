package com.lite.app

import com.lite.app.response.ResponseCall
import retrofit2.Call
import retrofit2.Callback

class MainPresenter{

    private var iPresenter: IPresenter
    constructor(presenter : IPresenter){
        iPresenter  = presenter
    }

    fun getNews(apiKey : String, page : Int){
        val apiService = NewsApi.create()
        val call = apiService.getApi("india", apiKey, page)
        call.enqueue(object : Callback<ResponseCall> {
            override fun onResponse(call: Call<ResponseCall>, response: retrofit2.Response<ResponseCall>?) {
                if(response!= null && response.isSuccessful){
                    iPresenter.onSuccess(response.body()?.articles)
                }
            }
            override fun onFailure(call: Call<ResponseCall>, t: Throwable) {
                iPresenter.onFailure()
            }
        })
    }

}