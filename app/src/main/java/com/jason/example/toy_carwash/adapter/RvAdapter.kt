package com.jason.example.toy_carwash.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jason.example.toy_carwash.R
import com.jason.example.toy_carwash.model.CarWashModel

class RvAdapter (val content : Context, val List : MutableList<CarWashModel>) : RecyclerView.Adapter<RvAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RvAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item, parent,false)

        return  ViewHolder(v)
    }
    override fun onBindViewHolder(holder: RvAdapter.ViewHolder, position: Int) {
        holder.bindItems(List[position])
    }

    override fun getItemCount(): Int {
        return List.size
    }
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
       fun bindItems(item : CarWashModel){
           val rvText = itemView.findViewById<TextView>(R.id.memoText)

           val rvDateText = itemView.findViewById<TextView>(R.id.dateText)

           rvText.text = item.Loc
           rvDateText.text = item.washDate

       }
    }
}