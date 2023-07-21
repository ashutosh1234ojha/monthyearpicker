package com.example.monthyearpicker.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.monthyearpicker.MonthData
import com.example.monthyearpicker.R
import com.example.monthyearpicker.YearData
import com.example.monthyearpicker.YearGridAdapter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MyDialogFragmentTest(val function: (ar: String) -> Unit) : DialogFragment() {

    var year: String = ""
    var month: String = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_dailog_test, container, false)

        val ok_action = view.findViewById<TextView>(R.id.ok_action)

        val calendar = Calendar.getInstance()
        val formatter = SimpleDateFormat("YYYY-MMM", Locale.getDefault())
        val currentMonthYear = formatter.format(calendar.time)

        year = currentMonthYear.split("-")[0]
        month = currentMonthYear.split("-")[1]
        ok_action.setOnClickListener {
            function("hell")
            this.dismiss()
        }

//
//        val adapterMonth = MonthsGridAdapter() { month ->
//
//        }
        val adapterYear = YearGridAdapterTest() { y ->
            year = y
            //  adapterMonth.setData(getMonths(), year, month)
        }

        val rvMonth = view.findViewById<RecyclerView>(R.id.rvMonth)
        val rvYear = view.findViewById<RecyclerView>(R.id.rvYear)
        rvMonth.apply {
            //   adapter = adapterMonth
            layoutManager = GridLayoutManager(requireContext(), 3)
        }

        rvYear.apply {
            adapter = adapterYear
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvYear)
        //  adapterMonth.setData(getMonths(), year,month)
        adapterYear.setData(getYears(), year)
        rvYear.scrollToPosition(4)

//        val btnMonth = view.findViewById<Button>(R.id.btnMonth)
//        btnMonth.setOnClickListener {
//            rvMonth.visibility = View.VISIBLE
//            rvYear.visibility = View.GONE
//        }
//
//        val btnYear = view.findViewById<Button>(R.id.btnYear)
//        btnYear.setOnClickListener {
//            rvMonth.visibility = View.GONE
//            rvYear.visibility = View.VISIBLE
//
//        }

        rvYear.visibility = View.VISIBLE


        return view.rootView
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

    fun getYears(): ArrayList<YearData> {
        val list = ArrayList<YearData>()
        val currentYear: Int = Calendar.getInstance().get(Calendar.YEAR)
        val startYear = currentYear - 5
        for (year in startYear..currentYear) {
            list.add(YearData(year.toString(), false))
        }
        return list

    }
}