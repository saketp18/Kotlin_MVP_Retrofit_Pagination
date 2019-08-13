package com.lite.app

import com.lite.app.response.ResponseCall
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/everything")
    fun getApi(@Query("q") query: String, @Query("apiKey") apiKey:String, @Query("page") page : Int): Call<ResponseCall>

    companion object Client {
        private val BASE_URL: String = "https://newsapi.org/"
        fun create(): NewsApi {
            val httpLogging: HttpLoggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor(httpLogging).build()
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient).build()

            return retrofit.create(NewsApi::class.java)
        }
    }
}