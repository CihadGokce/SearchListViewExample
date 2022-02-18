package com.cihadgokce.searchitemlist.core.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cihadgokce.searchitemlist.R
import com.cihadgokce.searchitemlist.model.SatelliteListItem
import com.example.searchrecyclerviewexample.DetailsActivity
import java.util.*
import kotlin.collections.ArrayList


class SatelliteAdapter( var mContext: Context,private var items: ArrayList<SatelliteListItem>) :
    RecyclerView.Adapter<SatelliteAdapter.ViewHolder>(), Filterable {


    var satelliteFilterList = ArrayList<SatelliteListItem>()


    init {
        satelliteFilterList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_satellite_layout,
                parent,
                false
            )
        )

    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items.get(position)

        holder.tvName.text = item.name.toString()
        holder.tvActive.text = item.active.toString()
        if(item.active == true){
            holder.icon.setImageResource(R.drawable.satellite_active_icon)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, DetailsActivity::class.java)
            intent.putExtra ("satelliteItemId", satelliteFilterList[position].id)
            mContext.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return satelliteFilterList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName : TextView = view.findViewById(R.id.tv_name)
        val tvActive : TextView = view.findViewById(R.id.tv_active)
        val icon : ImageView = view.findViewById(R.id.icon)
    }

    // Liste içinde verileri aramak için
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    satelliteFilterList = items
                } else {
                    val resultList = ArrayList<SatelliteListItem>()
                    for (row in items) {
                        if (row.name.toString().lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    satelliteFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = satelliteFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                satelliteFilterList = results?.values as ArrayList<SatelliteListItem>
                notifyDataSetChanged()
            }

        }
    }



}


