package com.gmail.progstrl.vetrf2.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.gmail.progstrl.vetrf2.R
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayout


class example : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)


        val words = arrayListOf("One", "Two", "Three", "Four", "Five")

        var pager = findViewById<ViewPager2>(R.id.pager)
        pager.adapter = PagerAdapter(this, words)

        var tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        TabLayoutMediator(tabLayout, pager){tab, position ->
            tab.text = "${position + 1}" + " position"
        }.attach()


        tabLayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@example, "Tab ${tab?.text} reselected", Toast.LENGTH_SHORT).show()

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@example, "Tab ${tab?.text} unselected", Toast.LENGTH_SHORT).show()

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(this@example, "Tab ${tab?.text} selected", Toast.LENGTH_SHORT).show()
            }
        })



    }
}