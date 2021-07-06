package com.gmail.progstrl.vetrf2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.gmail.progstrl.vetrf2.main.BaseNameActivity
import com.gmail.progstrl.vetrf2.main.example

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var button: Button
    private lateinit var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionBarSet()
        fillFields()
    }

    private fun actionBarSet() {
        val toolbar =
            findViewById<Toolbar>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
//        val actionBar = supportActionBar
//        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun fillFields() {
         button = findViewById(R.id.btnOpenBaseName)
        button.setOnClickListener(this)
        button2 = findViewById(R.id.btnOpenBaseName2)
        button2.setOnClickListener(View.OnClickListener {
            val intent: Intent =  Intent(this,example::class.java)
            startActivity(intent)
        })
    }

    override fun onClick(v: View?) {
        val intent: Intent =  Intent(this,BaseNameActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.iBase -> {
                val intent: Intent = Intent(this,BaseNameActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}