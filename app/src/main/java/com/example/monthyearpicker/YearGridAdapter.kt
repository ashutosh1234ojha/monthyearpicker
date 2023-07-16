package com.example.monthyearpicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class YearGridAdapter(val function: (year: String) -> Unit) :
    RecyclerView.Adapter<YearGridAdapter.RecyclerViewHolder>() {

    lateinit var courseDataArrayList: ArrayList<YearData>
    lateinit var year: String


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        // Inflate Layout
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_year, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        // Set the data to textview and imageview.
        val context = holder.itemView.context


        val recyclerData: YearData = courseDataArrayList!![position]
        holder.tvYear.text = courseDataArrayList?.get(position)?.str
        holder.tvYear.setOnClickListener {
            function(courseDataArrayList?.get(position)?.str!!)
        }
        holder.tvYear.setOnClickListener {
            year=courseDataArrayList?.get(position)?.str!!
            function(year)
            notifyDataSetChanged()
        }
        if (courseDataArrayList?.get(position)?.str.equals(year)) {
            holder.tvYear.background =
                ContextCompat.getDrawable(context, R.drawable.rect_selected_drawable)

        } else {
            holder.tvYear.background =
                ContextCompat.getDrawable(context, R.drawable.rect_unselected_drawable)

        }
    }

    override fun getItemCount(): Int {
        // this method returns the size of recyclerview
        return courseDataArrayList?.size ?: 0
    }

    fun setData(list: ArrayList<YearData>, y: String) {
        //  courseDataArrayList?.clear()
        year = y
        courseDataArrayList = list
        //  courseDataArrayList?.addAll(list)
        notifyDataSetChanged()

    }

    // View Holder Class to handle Recycler View.
    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvYear: TextView

        init {
            tvYear = itemView.findViewById<TextView>(R.id.tvYear)
        }
    }
}