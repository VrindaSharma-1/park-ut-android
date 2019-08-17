package com.example.parkut

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvAdapter(val userList: ArrayList<Garage_Model>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.adapter_item_layout, p0, false)
        return ViewHolder(v);
    }
    override fun getItemCount(): Int {
        return userList.size
    }
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.address?.text = userList[p1].address
        p0.id?.text = userList[p1].id
        p0.name?.text = userList[p1].name

        p0.spot?.text = userList[p1].spots
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val address = itemView.findViewById<TextView>(R.id.gaddress)
        val id = itemView.findViewById<TextView>(R.id.gid)
        val name = itemView.findViewById<TextView>(R.id.gname)

        val spot = itemView.findViewById<TextView>(R.id.gspot)


    }
}