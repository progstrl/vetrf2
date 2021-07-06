package com.gmail.progstrl.vetrf2.main

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.opengl.Visibility
import android.os.Debug
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.progstrl.vetrf2.R

class BaseNameTabAdapter(private val context: Context, private val tabsName: List<String>) :
    RecyclerView.Adapter<BaseNameTabAdapter.RVHolder>() {
    private var currentPosition: Int = 0
    var myOnClickListener: MyOnClickListener? = null


    inner class RVHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val textView: TextView = view.findViewById(R.id.tvItemTab) as TextView
        val textLine: TextView = view.findViewById(R.id.tvLine)


        init {
            view.setOnClickListener(this)
//            view.setOnClickListener(View.OnClickListener {
//                currentPosition = adapterPosition
//                this@BaseNameTabAdapter.notifyDataSetChanged()
//
//                 myOnClickListener?.myOnClick(it, getAdapterPosition(),ConstantsMain.IS_BASENAME_TAB_ADAPTER)

//            })
        }

        override fun onClick(v: View?) {
            currentPosition = adapterPosition
            this@BaseNameTabAdapter.notifyDataSetChanged()

            myOnClickListener?.myOnClick(v, currentPosition,ConstantsMain.IS_BASENAME_TAB_ADAPTER)
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseNameTabAdapter.RVHolder =
        RVHolder(LayoutInflater.from(context).inflate(R.layout.item_tab, parent, false))


    override fun getItemCount(): Int = tabsName.size

    override fun onBindViewHolder(holder: BaseNameTabAdapter.RVHolder, position: Int) {
        holder.textView.text = tabsName[position]
        holder.textView.setTag(R.string.TabPosition, position)
        if (currentPosition == position) {
            holder.textLine.visibility = View.VISIBLE
        } else {
            holder.textLine.visibility = View.GONE
        }
//        holder.textView.setOnClickListener(View.OnClickListener {
//            currentPosition = holder.adapterPosition
//            this@BaseNameTabAdapter.notifyDataSetChanged()
//
//            myOnClickListener?.myOnClick(it, currentPosition,ConstantsMain.IS_BASENAME_TAB_ADAPTER)
//        })
    }




}