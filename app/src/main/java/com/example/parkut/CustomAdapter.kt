package com.example.parkut

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.ArrayList


class CustomAdapter(private val context: Context, private val usersModelArrayList: ArrayList<Garage_Model>) :
    BaseAdapter() {

    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return usersModelArrayList.size
    }

    override fun getItem(position: Int): Any {
        return usersModelArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.user, null, true)
            holder.address = convertView.findViewById(R.id.address) as TextView
            holder.id = convertView!!.findViewById(R.id.id) as TextView
            holder.name = convertView!!.findViewById(R.id.name) as TextView

         //   holder.spot = convertView.findViewById(R.id.spots) as TextView

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }
        holder.address!!.text = usersModelArrayList[position].getAddresses()
        holder.id!!.text = "Id - " + usersModelArrayList[position].getIds()
        holder.name!!.text = usersModelArrayList[position].getNames()

        //holder.spot!!.text ="Spots :"+  usersModelArrayList[position].getSpotss()
        return convertView
    }

    private inner class ViewHolder {

        var name: TextView? = null
        var address: TextView? = null
        var id: TextView? = null
       // var spot: TextView? = null
    }

}