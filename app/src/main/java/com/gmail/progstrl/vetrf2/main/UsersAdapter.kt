package com.gmail.progstrl.vetrf2.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.progstrl.vetrf2.R
import com.gmail.progstrl.vetrf2.database.Users

class UsersAdapter(private val itemLayouteId: Int) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    private var usersList: List<Users>? = null
    private var myOnClickListener: MyOnClickListener? =
        null

    fun setMyOnClickListener(myOnClickListener: MyOnClickListener?) {
        this.myOnClickListener = myOnClickListener
    }

    fun setUsersList(usersList: List<Users>?) {
        this.usersList = usersList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(itemLayouteId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        // TextView tvUsers =  holder.tvUsers;
        holder.tvUsers.text = usersList!![position].name
    }

    override fun getItemCount(): Int {
        return if (usersList == null) 0 else usersList!!.size
    }

    fun getUser(position: Int): Users {
        return usersList!![position]
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var tvUsers: TextView
        override fun onClick(view: View) {
            if (myOnClickListener != null) {
                myOnClickListener!!.myOnClick(view, adapterPosition,ConstantsMain.IS_USERS_ADAPTER)
            }
        }

        init {
            tvUsers = itemView.findViewById(R.id.tvUsers)
            tvUsers.setOnClickListener(this)
        }
    }



}