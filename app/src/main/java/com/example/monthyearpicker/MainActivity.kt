package com.example.monthyearpicker

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.monthyearpicker.MonthPickerDialog.Builder
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  setNormalPicker()
        val dialogFragment = MyDialogFragment() { ar ->
            Toast.makeText(this, "msg " + ar, Toast.LENGTH_SHORT).show()

        }
        dialogFragment.show(supportFragmentManager, "My  Fragment")
    }

    private fun setNormalPicker() {
        //  setContentView(R.layout.activity_main)
        val today = Calendar.getInstance()

        val monthPicker = findViewById<Button>(R.id.month_picker)
        monthPicker.setOnClickListener {

            val builder: Builder =
                MonthPickerDialog.Builder(this, object : MonthPickerDialog.OnDateSetListener {
                    override fun onDateSet(selectedMonth: Int, selectedYear: Int) {
                        Log.d(
                            "TAG",
                            "selectedMonth : $selectedMonth selectedYear : $selectedYear"
                        )
                        Toast.makeText(
                            this@MainActivity,
                            "Date set with month$selectedMonth year $selectedYear",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }, today[Calendar.YEAR], today[Calendar.MONTH])


//        findViewById(R.id.month_picker).setOnClickListener(View.OnClickListener {
//            val builder: MonthPickerDialog.Builder =
//                MonthPickerDialog.Builder(this@MainActivity, object : MonthPickerDialog.OnDateSetListener() {
//                    fun onDateSet(selectedMonth: Int, selectedYear: Int) {
//                        Log.d(
//                            "TAG",
//                            "selectedMonth : $selectedMonth selectedYear : $selectedYear"
//                        )
//                        Toast.makeText(
//                            this@MainActivity,
//                            "Date set with month$selectedMonth year $selectedYear",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }, today[Calendar.YEAR], today[Calendar.MONTH])


            val min = Calendar.getInstance()
            min.add(Calendar.YEAR, -5)

            builder.setActivatedMonth(Calendar.JULY)
                .setMinYear(min.get(Calendar.YEAR))
                .setActivatedYear(today.get(Calendar.YEAR))
                .setMaxYear(today.get(Calendar.YEAR))
                .setMinMonth(min.get(Calendar.MONTH))
                .setTitle("Select trading month")
                .setMonthRange(
                    Calendar.JANUARY,
                    Calendar.DECEMBER
                )
                // .setMaxMonth(Calendar.OCTOBER)
                // .setYearRange(1890, 1890)
                // .setMonthAndYearRange(Calendar.FEBRUARY, Calendar.OCTOBER, 1890, 1890)
                //.showMonthOnly()
                // .showYearOnly()
//            .setOnMonthChangedListener(object : OnMonthChangedListener() {
//                fun onMonthChanged(selectedMonth: Int) {
//                    Log.d(TAG, "Selected month : $selectedMonth")
//                    // Toast.makeText(MainActivity.this, " Selected month : " + selectedMonth, Toast.LENGTH_SHORT).show();
//                }
//            })
//            .setOnYearChangedListener(object : OnYearChangedListener() {
//                fun onYearChanged(selectedYear: Int) {
//                    Log.d("TAG", "Selected year : $selectedYear")
//                    // Toast.makeText(MainActivity.this, " Selected year : " + selectedYear, Toast.LENGTH_SHORT).show();
//                }
//            })
                .setOnYearChangedListener { year ->
                    if (year == 2023) {
                        builder.setMonthRange(
                            Calendar.JANUARY,
                            Calendar.JULY
                        )
                    }

                }
                .build()
                .show()
        }
//        findViewById(R.id.date_picker).setOnClickListener(View.OnClickListener {
//            val cal = Calendar.getInstance()
//            val dialog = DatePickerDialog(
//                this@MainActivity, null, 2017,
//                cal[Calendar.MONTH], cal[Calendar.DATE]
//            )
//            dialog.show()
//        })
    }


}