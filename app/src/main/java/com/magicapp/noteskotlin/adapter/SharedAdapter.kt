package com.magicapp.noteskotlin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.magicapp.noteskotlin.R
import com.magicapp.noteskotlin.managers.PrefsManager
import com.magicapp.noteskotlin.model.Shared


class SharedAdapter(context: Context, var list: ArrayList<Shared>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val prefsManager = PrefsManager.getInstance(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shared_pref, parent, false)
        return NoteViewHolder(view)
    }

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDate: TextView = view.findViewById(R.id.txt_date)
        val tvText: TextView = view.findViewById(R.id.txt_message)
        val ivPoint: ImageView = view.findViewById(R.id.iv_isRead)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val note: Shared = list[position]
        if (holder is NoteViewHolder) {

            holder.tvDate.text = note.date
            holder.tvText.text = note.message
            if (note.isRead!!)
                holder.ivPoint.visibility = View.INVISIBLE


            holder.tvText.setOnClickListener {
                list.remove(note)
                note.isRead = true
                list.add(position, note)
                saveNewList(list)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addNote(note: Shared) {
        list.add(note)
        saveNewList(list)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun saveNewList(list: ArrayList<Shared>) {
        prefsManager!!.saveArrayList(PrefsManager.KEY_LIST, list)
        notifyDataSetChanged()
    }
}