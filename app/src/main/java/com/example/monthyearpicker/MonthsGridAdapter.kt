package com.example.monthyearpicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class MonthsGridAdapter(val function: (String?) -> Unit) :
    RecyclerView.Adapter<MonthsGridAdapter.RecyclerViewHolder>() {

    lateinit var courseDataArrayList: ArrayList<MonthData>
    var year: String = ""
    var month: String = ""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_month, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        // Set the data to textview and imageview.
        val context = holder.itemView.context
        val recyclerData: MonthData = courseDataArrayList[position]
        holder.tvMonth.text = courseDataArrayList.get(position).str
        holder.tvMonth.setOnClickListener {
            month= courseDataArrayList.get(position).str
            function(month)
            notifyDataSetChanged()
        }
        if (courseDataArrayList.get(position).str.equals(month)) {
            holder.tvMonth.background =
                ContextCompat.getDrawable(context, R.drawable.selected_drawable)

        } else {
            holder.tvMonth.background =
                ContextCompat.getDrawable(context, R.drawable.unselected_drawable)

        }
        if (year.equals("2023") && courseDataArrayList[position].isEnabled) {
            holder.tvMonth.setTextColor(context.getColor(R.color.black))
        } else {
            holder.tvMonth.setTextColor(context.getColor(R.color.fontBlackDisable))
        }
    }

    override fun getItemCount(): Int {
        return courseDataArrayList?.size ?: 0
    }

    fun setData(list: ArrayList<MonthData>, y: String, m: String) {
        courseDataArrayList = list
        year = y
        month = m
        notifyDataSetChanged()

    }

    // View Holder Class to handle Recycler View.
    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMonth: TextView

        init {
            tvMonth = itemView.findViewById<TextView>(R.id.tvMonth)
        }
    }
}