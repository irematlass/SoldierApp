package com.example.soldierapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class MyAdapter(val myList: ArrayList<String>) :
    RecyclerView.Adapter<MyAdapter.SoldierViewHolder>() {
    fun addNewItem(newItem: String) {
        myList.add(newItem)
        notifyDataSetChanged()
    }

    class SoldierViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoldierViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return SoldierViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: SoldierViewHolder, position: Int) {
        holder.view.itemNumber.text = (position + 1).toString()
        holder.view.itemString.text = myList[position]
    }
}