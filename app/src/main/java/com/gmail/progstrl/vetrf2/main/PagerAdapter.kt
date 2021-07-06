package com.gmail.progstrl.vetrf2.main

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.gmail.progstrl.vetrf2.R

class PagerAdapter (private val context: Context, private val words: List<String>): RecyclerView.Adapter<PagerAdapter.PageHolder>(){

    inner class PageHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
        val button1: Button = view.findViewById(R.id.button)
        val button2: Button = view.findViewById(R.id.button2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder =
        PageHolder(LayoutInflater.from(context).inflate(R.layout.page_layout,parent,false))


    override fun getItemCount(): Int = words.size

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        if(position == 1){
            holder.button1.isVisible = false;
        } else if(position == 2){
            holder.button2.isVisible = false;
        }
        holder.textView.text = words[position]
    }

}