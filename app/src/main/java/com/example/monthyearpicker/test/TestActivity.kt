package com.example.monthyearpicker.test

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.monthyearpicker.MyDialogFragment
import com.example.monthyearpicker.R

class TestActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        //  setNormalPicker()
        val dialogFragment = MyDialogFragmentTest() { ar ->
            Toast.makeText(this, "msg " + ar, Toast.LENGTH_SHORT).show()

        }
        dialogFragment.show(supportFragmentManager, "My  Fragment")
    }
}