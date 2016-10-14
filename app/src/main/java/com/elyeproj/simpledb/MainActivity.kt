package com.elyeproj.simpledb

import android.content.ContentValues
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = DbHelper(this)
        loadData()
    }

    private fun loadData() {
        txt_all_data.text = dbHelper.getAllText()
    }

    fun insertData(view: View) {
        if (!TextUtils.isEmpty(edit_entry.text)) {
            dbHelper.insertText(edit_entry.text.toString())
            edit_entry.text.clear()
            loadData()
        }
    }

    fun clearData(view: View) {
        dbHelper.clearDbAndRecreate()
        loadData()
    }
}
