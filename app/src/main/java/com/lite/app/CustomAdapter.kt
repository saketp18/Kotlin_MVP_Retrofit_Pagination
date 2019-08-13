package com.lite.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lite.app.response.Article

class CustomAdapter() : RecyclerView.Adapter<CustomAdapter.ListHolder>(){

    var articles : ArrayList<Article>? = ArrayList<Article>()
    class ListHolder(item : View) : RecyclerView.ViewHolder(item){

        var title : TextView = item.findViewById(R.id.title)
        var description : TextView = item.findViewById(R.id.description)

    }

    fun setData(article: ArrayList<Article>?){
        for(items in article!!){
            articles!!.add(items)
        }
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ListHolder(view)
    }

    override fun getItemCount(): Int {
        return articles!!.size
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.title.setText(articles!!.get(position).title)
        holder.description.setText(articles!!.get(position).description)
    }
}