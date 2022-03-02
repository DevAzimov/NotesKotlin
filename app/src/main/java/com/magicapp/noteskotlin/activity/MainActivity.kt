package com.magicapp.noteskotlin.activity

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.reflect.TypeToken
import com.magicapp.noteskotlin.R
import com.magicapp.noteskotlin.adapter.SharedAdapter
import com.magicapp.noteskotlin.fragments.DialogFragment
import com.magicapp.noteskotlin.managers.PrefsManager
import com.magicapp.noteskotlin.model.Shared
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: SharedAdapter
    private lateinit var prefsManager: PrefsManager

    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()


    }

    fun initViews() {
        prefsManager = PrefsManager.getInstance(this)!!
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        adapter = SharedAdapter(this, getNotes())
        recyclerView.adapter = adapter

        val btnAdd: FloatingActionButton = findViewById(R.id.fab_btn)
        btnAdd.setOnClickListener {
            callDialog()
        }
    }

    fun callDialog() {
        val dialogFragment = DialogFragment()
        dialogFragment.show(supportFragmentManager, null)

        dialogFragment.saveClick = {
            val note = Shared(getDate(), it, false)
            adapter.addNote(note)
        }
    }

//    fun refreshAdapter(lists: ArrayList<Shared>) {
//        val adapter = SharedAdapter(this, lists)
//        recyclerView!!.adapter = adapter
//    }

    private fun getNotes(): ArrayList<Shared> {
        val type: Type = object : TypeToken<ArrayList<Shared>>() {}.type
        return prefsManager.getArrayList(PrefsManager.KEY_LIST, type)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(): String? {
        val date: Date = Calendar.getInstance().time
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        return simpleDateFormat.format(date).toString()
    }

}

//    fun addList(): ArrayList<Shared> {
//        val list: ArrayList<Shared> = ArrayList()
//        list.add(Shared("23:01:2012", "Nimadur voqealar bulib utdi", true))
//        list.add(Shared("23:01:2012", "Nimadur voqealar bulib utdi", false))
//        list.add(Shared("23:01:2012", "Nimadur voqealar bulib utdi", true))
//        list.add(Shared("23:01:2012", "Nimadur voqealar bulib utdi", false))
//        list.add(Shared("23:01:2012", "Nimadur voqealar bulib utdi", true))
//        list.add(Shared("23:01:2012", "Nimadur voqealar bulib utdi", false))
//        list.add(Shared("23:01:2012", "Nimadur voqealar bulib utdi", true))
//        list.add(Shared("23:01:2012", "Nimadur voqealar bulib utdi", false))
//        list.add(Shared("23:01:2012", "Nimadur voqealar bulib utdi", false))
//        list.add(Shared("23:01:2012", "Nimadur voqealar bulib utdi", true))
//        list.add(Shared("23:01:2012", "Nimadur voqealar bulib utdi", false))
//        list.add(Shared("23:01:2012", "Nimadur voqealar bulib utdi", false))
//        return list }
