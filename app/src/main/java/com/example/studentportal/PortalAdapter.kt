package com.example.studentportal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import kotlinx.android.synthetic.main.content_main.view.*

class PortalAdapter (private val portals:List<Portal>, val clickListener: (Portal) -> Unit): RecyclerView.Adapter<PortalAdapter.Viewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.content_main, parent, false)
        )
    }
    override fun getItemCount(): Int {
        return portals.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind(portals[position],clickListener)
    }

    inner class Viewholder(itemView:View) :
        RecyclerView.ViewHolder(itemView){

        fun bind(portal: Portal, clickListener: (Portal) -> Unit){
            itemView.tvPortalTitle.text = portal.portalTitle
            itemView.tvUrl.text = portal.portalUrl
            itemView.setOnClickListener{clickListener(portal)
            }
        }
    }
}
