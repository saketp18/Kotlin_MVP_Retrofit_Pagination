package com.lite.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lite.app.response.Article
import com.lite.app.response.ResponseCall
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity(), IPresenter {


    private val API_KEY : String = "a6ae4f566f944b93873fc9e93421a5e6"
    private var isLoading : Boolean = true
    private var pastVisibleItem:Int = 0
    private var totalItemCount : Int = 0
    private var visbileItem : Int = 0
    private var previousItem: Int = 0
    private var viewThreshold : Int  = 10
    private lateinit var listView : RecyclerView
    private lateinit var  mAdapter : CustomAdapter
    private lateinit var  mlayoutManager : LinearLayoutManager
    private lateinit var mMainPresenter: MainPresenter
    private var pageNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMainPresenter = MainPresenter(this)
        mlayoutManager = LinearLayoutManager(this)
        mAdapter = CustomAdapter()
        listView = findViewById<RecyclerView>(R.id.list).apply {
            layoutManager = mlayoutManager
            adapter = mAdapter
        }
        listView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                visbileItem = mlayoutManager.childCount
                totalItemCount = mlayoutManager.itemCount
                pastVisibleItem = mlayoutManager.findFirstVisibleItemPosition()

                if(dy>0) {
                    if (isLoading && totalItemCount > previousItem){
                        isLoading = false
                        previousItem = totalItemCount

                    }
                    if(!isLoading &&(totalItemCount - visbileItem)<=(pastVisibleItem + viewThreshold) && pageNumber < 6){
                        pageNumber++
                        mMainPresenter.getNews(API_KEY, pageNumber)
                    }
                }
            }
        })

        mMainPresenter.getNews(API_KEY, pageNumber)

    }
    override fun onSuccess(articles: ArrayList<Article>?) {
        mAdapter.setData(articles)
    }

    override fun onFailure() {
        Toast.makeText(applicationContext, "Please connect to internet!", Toast.LENGTH_SHORT).show()
    }
}
