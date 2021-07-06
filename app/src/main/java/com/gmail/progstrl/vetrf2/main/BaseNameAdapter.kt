package com.gmail.progstrl.vetrf2.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.progstrl.vetrf2.R
import com.gmail.progstrl.vetrf2.database.BaseName

class BaseNameAdapter(private val itemLayoutId: Int) :
    RecyclerView.Adapter<BaseNameAdapter.ViewHolder>() {
    private var baseNameList: List<BaseName>? = null
    private var myOnClickListener: MyOnClickListener? = null


    fun setBaseNameList(baseNameList: List<BaseName>?) {
        this.baseNameList = baseNameList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(itemLayoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val tvBaseName = holder.tvBaseName
        val tvBaseGuid = holder.tvBaseGuid
        tvBaseName.text = baseNameList!![position].name
        tvBaseGuid.text = baseNameList!![position].issuerid
    }

    override fun getItemCount(): Int {
        return if (baseNameList == null) 0 else baseNameList!!.size
    }

    fun getObjectById(position: Int): BaseName {
        return baseNameList!![position]
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var tvBaseName: TextView
        var tvBaseGuid: TextView
        override fun onClick(view: View) {
            if (myOnClickListener != null) {
                myOnClickListener!!.myOnClick(view, adapterPosition, ConstantsMain.IS_BASENAME_ADAPTER)
            }
        }

        init {
            tvBaseName = itemView.findViewById(R.id.etBaseName)
            tvBaseGuid = itemView.findViewById(R.id.tvBaseGuid)
            tvBaseName.setOnClickListener(this)
            tvBaseGuid.setOnClickListener(this)
        }
    }

    //    public interface MyOnClickListener{
    //        void myOnClick(View view,int position);
    //    }
    fun setMyOnClickListener(myOnClickListener: MyOnClickListener?) {
        this.myOnClickListener = myOnClickListener
    }

}