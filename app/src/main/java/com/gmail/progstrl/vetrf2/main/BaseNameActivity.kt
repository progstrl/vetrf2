package com.gmail.progstrl.vetrf2.main

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.progstrl.vetrf2.R
import com.gmail.progstrl.vetrf2.database.BaseName

class BaseNameActivity : AppCompatActivity(), View.OnClickListener, MyOnClickListener {

    private lateinit var mViewModel: BaseNameViewModel
    private val llGroupEditBase: LinearLayout? = null
    private var etName: EditText? = null
    private var btnAddDataBaseNames: Button? = null
    private lateinit var recyclerView: RecyclerView
    private var baseNameAdapter: BaseNameAdapter? = null
    private var etIssuerID: EditText? = null
//    private var cbIsTesting: CheckBox? = null
    private lateinit var btnGroupVisible: Button
    private var groupAddBase: Group? = null
    private var btnLoadByFile: Button? = null
    private val baseNameByTxt: BaseName? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_name)

        actionBarSet()
        fillfield()
        listenerSetup()
        recyclerSetup()
        observerSetup()
    }

    private fun actionBarSet() {
        val toolbar =
            findViewById<Toolbar>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
//        val actionBar = supportActionBar
//        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun fillfield() {

//        setTitle("Базы ХС");
        mViewModel = ViewModelProvider(this).get(BaseNameViewModel::class.java)

        etName = findViewById(R.id.etName)
        etIssuerID = findViewById(R.id.tvIssuerID)
        btnAddDataBaseNames = findViewById(R.id.btnAddDataBaseNames)
        recyclerView = findViewById(R.id.rvBaseName)
//        cbIsTesting = findViewById(R.id.cbIsTesting)
        btnGroupVisible = findViewById(R.id.btnGroupVisible)
        btnLoadByFile = findViewById(R.id.btnLoadByFile)
        groupAddBase = findViewById(R.id.groupAddBase)
    }

    private fun listenerSetup() {
        btnAddDataBaseNames!!.setOnClickListener(this)
        btnGroupVisible.setOnClickListener(this)
        btnLoadByFile!!.setOnClickListener(this)
    }



    private fun recyclerSetup() {
        baseNameAdapter = BaseNameAdapter(R.layout.item_basename)
        baseNameAdapter!!.setMyOnClickListener(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = baseNameAdapter
        val dividerItemDecoration =
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnAddDataBaseNames -> {
                if (!etIssuerID!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                    val issuerID: String

                        issuerID = etIssuerID!!.text.toString().trim { it <= ' ' }

                    val baseName =
                        BaseName(etName!!.text.toString().trim { it <= ' ' }, issuerID)

                    mViewModel.insertBaseName(baseName)
                    etName!!.setText("")
                    etIssuerID!!.setText("")
                }
                groupAddBase!!.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                btnGroupVisible.text = getString(R.string.add_base)
            }
            R.id.btnGroupVisible -> {

                if(groupAddBase!!.visibility == View.GONE){
                    btnGroupVisible.text = getString(R.string.cancel)
                    groupAddBase!!.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                } else{
                    btnGroupVisible.text = getString(R.string.add_base)
                    groupAddBase!!.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                }
            }
            R.id.btnLoadByFile -> {
                loadBaseSettingByTxt()
            }
        }
    }

    private fun loadBaseSettingByTxt() {
//        val intent = Intent(this, FilePickerActivity::class.java)
//        startActivityForResult(intent, ConstantsMain.REQUEST_FILE_PATH)
    }


    override fun myOnClick(view: View?, position: Int,key: String) {
        val baseName = baseNameAdapter!!.getObjectById(position)
        //        MyApplication.get().setBaseSelected(baseName);
        val intent = Intent(this@BaseNameActivity, BaseNameSettingActivity::class.java)
        intent.putExtra(ConstantsMain.BASE_GUID, baseName.issuerid)
        intent.putExtra(ConstantsMain.API_KEY, baseName.apikey)
        intent.putExtra(ConstantsMain.BASE_NAME, baseName.name)
        startActivity(intent)
    }

    private fun observerSetup() {
        mViewModel.getAllBaseName().observe(this, Observer<List<BaseName>>{
            baseNameAdapter?.setBaseNameList(it)
        })

    }
}




