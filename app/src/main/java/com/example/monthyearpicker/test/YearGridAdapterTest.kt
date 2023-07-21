package com.example.monthyearpicker.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.monthyearpicker.MonthData
import com.example.monthyearpicker.MonthsGridAdapter
import com.example.monthyearpicker.R
import com.example.monthyearpicker.YearData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class YearGridAdapterTest(val function: (year: String) -> Unit) :
    RecyclerView.Adapter<YearGridAdapterTest.RecyclerViewHolder>() {

    lateinit var courseDataArrayList: ArrayList<YearData>
    lateinit var year: String


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        // Inflate Layout
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_year_test, parent, false)
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
            year = courseDataArrayList?.get(position)?.str!!
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

        val adapterMonth = MonthsGridAdapterTest() { month ->

        }


        holder.rvMonth.apply {
            adapter = adapterMonth
            layoutManager = GridLayoutManager(context, 3)
        }

        adapterMonth.setData(getMonths(), year,"")

    }
    fun getMonths(): ArrayList<MonthData> {
        val monthList = ArrayList<MonthData>(12)
        for (i in 0..11) {
            monthList.add(MonthData("", false))
        }

        val calendar = Calendar.getInstance()
        val calenderCurrent = Calendar.getInstance()

        val monthFormat = SimpleDateFormat("MMM", Locale.getDefault())
        val currentMonth = monthFormat.format(calendar.time)
        monthList.set(calendar.get(Calendar.MONTH), MonthData(currentMonth, false))
        for (i in 1..11) {
            calendar.add(Calendar.MONTH, -1)
            val month = monthFormat.format(calendar.time)
            if (calenderCurrent.get(Calendar.MONTH) < calendar.get(Calendar.MONTH)) {
                monthList.set(calendar.get(Calendar.MONTH), MonthData(month, false))
            } else {
                monthList.set(calendar.get(Calendar.MONTH), MonthData(month, true))
            }
        }
        return monthList
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
        val rvMonth: RecyclerView

        init {
            tvYear = itemView.findViewById<TextView>(R.id.tvYear)
            rvMonth = itemView.findViewById<RecyclerView>(R.id.rvMonthTest)
        }
    }
}