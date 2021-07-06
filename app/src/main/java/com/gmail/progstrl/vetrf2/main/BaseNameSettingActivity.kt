package com.gmail.progstrl.vetrf2.main

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
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
import com.gmail.progstrl.vetrf2.database.Users
import com.gmail.progstrl.vetrf2.main.MyDialogFragment.DialogFragmentListener

class BaseNameSettingActivity() : AppCompatActivity(),
    View.OnClickListener, TextWatcher,
    DialogFragmentListener,
    MyOnClickListener {
    private lateinit var etBaseName: EditText

    //    private EditText etGuid;
    private lateinit var mViewModel: BaseNameSettingViewModel
    private lateinit var baseGuid: String
    private var isAlive = false
    private lateinit var etLogin: EditText
    private lateinit var etPassword: EditText
    private lateinit var etAPIKey: EditText
    private lateinit var etServiceID: EditText
    private lateinit var tvIssuerID: TextView

    //    private lateinit var cbIsTesting: CheckBox
    private lateinit var btnSaveSetting: Button
    private lateinit var rvUsers: RecyclerView
    private lateinit var rvTabTop: RecyclerView
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var baseNameTabAdapter: BaseNameTabAdapter
    private lateinit var etNewUser: EditText
    private lateinit var btnSaveUser: Button
    private lateinit var ibtnAddUser: ImageButton
    private lateinit var ibtnDeleteUser: ImageButton
    private lateinit var btnDeleteBase: Button
    private lateinit var btnViewEnterprise: Button
    private lateinit var cbAutoExchange: CheckBox
    private lateinit var etUserAutoExchange: EditText
    private lateinit var isTesting: String

    private lateinit var grpEntity: Group
    private lateinit var grpEnterprise: Group
    private lateinit var grpUsers: Group


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_name_setting)

        actionBarSet()
        fillfield()
        listenerSetup()
        recyclerSetup()
        observerSetup()
    }

    private fun actionBarSet() {
        val toolbar =
            findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        //        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private fun fillfield() {
        baseGuid = intent.getStringExtra(ConstantsMain.BASE_GUID)!!
        val apiKey = intent.getStringExtra(ConstantsMain.API_KEY)
        val baseName = intent.getStringExtra(ConstantsMain.BASE_NAME)
        mViewModel = ViewModelProvider(this).get(BaseNameSettingViewModel::class.java)
//        ViewModelProviders.of(
//            this,
//            ViewModelFactory(this.application, baseGuid, apiKey, isTesting)
//        ).get(
//            BaseNameSettingViewModel::class.java
//        )
        etBaseName = findViewById(R.id.etBaseName)
        //        etGuid = findViewById(R.id.etGuid);
        etLogin = findViewById(R.id.etLogin)
        etPassword = findViewById(R.id.etPassword)
        etAPIKey = findViewById(R.id.etAPIKey)
        etServiceID = findViewById(R.id.etServiceID)
        tvIssuerID = findViewById(R.id.tvIssuerID)
        ibtnAddUser = findViewById(R.id.ibtnAddUser)
        btnSaveSetting = findViewById(R.id.btnSaveSetting)
        rvUsers = findViewById(R.id.rvUsers)
        rvTabTop = findViewById(R.id.rvTabTop)
        etNewUser = findViewById(R.id.etNewUser)
        btnSaveUser = findViewById(R.id.btnSaveUser)
        ibtnDeleteUser = findViewById(R.id.ibtnDeleteUser)
        btnDeleteBase = findViewById(R.id.btnDeleteBase)
        btnViewEnterprise = findViewById(R.id.btnViewEnterprise)
        cbAutoExchange = findViewById(R.id.chbAutoExchange)
        etUserAutoExchange = findViewById(R.id.etUserAutoExchange)
//            cbIsTesting = findViewById(R.id.cbIsTesting)
        if (baseName != null) {
            etBaseName.setText(baseName)
        }
        if (baseGuid != null) {
            tvIssuerID.setText(baseGuid)
        }
        isAlive = mViewModel.isAlive

        grpEntity = findViewById(R.id.grpEntity)
        grpEnterprise = findViewById(R.id.grpEnterprise)
        grpUsers = findViewById(R.id.grpUsers)
    }

    private fun recyclerSetup() {
        usersAdapter = UsersAdapter(R.layout.item_user)
        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.adapter = usersAdapter
        usersAdapter.setMyOnClickListener(this)

        val tabsName = arrayListOf("Основные", "Предприятия", "Пользователи")
        baseNameTabAdapter = BaseNameTabAdapter(this, tabsName)
        baseNameTabAdapter.myOnClickListener = this;
        rvTabTop.adapter = baseNameTabAdapter
        rvTabTop.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvTabTop.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL))


    }

    private fun listenerSetup() {
        //btnBack.setOnClickListener(this);
        btnSaveSetting.setOnClickListener(this)
        btnSaveUser.setOnClickListener(this)
        ibtnAddUser.setOnClickListener(this)
        ibtnDeleteUser.setOnClickListener(this)
        btnDeleteBase.setOnClickListener(this)
        btnViewEnterprise.setOnClickListener(this)
        cbAutoExchange.setOnClickListener(this)
//        cbIsTesting.setOnClickListener(this)
        //        etGuid.addTextChangedListener(this);
        etLogin.addTextChangedListener(this)
        etPassword.addTextChangedListener(this)
        etAPIKey.addTextChangedListener(this)
        etServiceID.addTextChangedListener(this)
        etBaseName.addTextChangedListener(this)
        etUserAutoExchange.addTextChangedListener(this)
    }

    fun observerSetup() {
        if (baseGuid != null && isAlive == false) {
            //mViewModel.getBaseNameById(baseGuid)
            mViewModel.getBaseNameById(baseGuid!!).observe(this, Observer<List<BaseName>> {
                if (it.size == 1) {
                    fillFieldByBaseName(it[0])
                }
            })

//            mViewModel.baseNameMLD.observe(this, object : Observer<BaseName?>() {
//                fun onChanged(baseName: BaseName?) {
//                    baseName?.let { fillFieldByBaseName(it) }
//                }
//            })
//        }
//        if (baseGuid != null && !baseGuid.trim { it <= ' ' }.isEmpty()) {
//            mViewModel.allUsers.observe(this, object : Observer<List<Users?>?>() {
//                fun onChanged(users: List<Users?>?) {
//                    usersAdapter.setUsersList(users)
//                }
//            })
        }
    }


    private fun fillFieldByBaseName(baseName: BaseName) {
//        etGuid.setText(baseName.getGuid());
        etLogin.setText(baseName.login)
        etPassword.setText(baseName.password)
        etAPIKey.setText(baseName.apikey)
        etServiceID.setText(baseName.serviceid)
        tvIssuerID.text = baseName.issuerid
        if ((baseName.autoexchange == "1")) {
            cbAutoExchange.isChecked = true
        } else {
            cbAutoExchange.isChecked = false
        }

        etUserAutoExchange.setText(baseName.userautoexchange)
        btnSaveSetting.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        mViewModel.isAlive = true
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.ibtnAddUser -> {
                etNewUser.visibility = View.VISIBLE
                btnSaveUser.visibility = View.VISIBLE
                ibtnDeleteUser.visibility = View.GONE
                etNewUser.requestFocus()
                etNewUser.postDelayed(Runnable {
                    val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.showSoftInput(etNewUser, 0)
                }, 200)
            }
            R.id.btnSaveSetting -> {
                saveSetting()
            }
            R.id.btnSaveUser -> {
//                val newUser = etNewUser.text.toString()
//                if (!newUser.trim { it <= ' ' }
//                        .isEmpty() && (baseGuid != null) && !baseGuid.trim { it <= ' ' }.isEmpty()
//                ) {
//                    val user = Users(newUser, baseGuid)
//                    mViewModel.insertUser(user)
//                    etNewUser.setText("")
//                    etNewUser.visibility = View.GONE
//                    btnSaveUser.visibility = View.GONE
//                    //                    ibtnDeleteUser.setVisibility(View.GONE);
//                }
            }
            R.id.ibtnDeleteUser -> {
//                val userName = etNewUser.text.toString()
//                if (!userName.trim { it <= ' ' }
//                        .isEmpty() && (baseGuid != null) && !baseGuid.trim { it <= ' ' }
//                        .isEmpty()) {
//                    mViewModel.deleteUserByName(userName, baseGuid, isTesting)
//                    etNewUser.setText("")
//                    etNewUser.visibility = View.GONE
//                    //                    btnSaveUser.setVisibility(View.GONE);
//                    ibtnDeleteUser.visibility = View.GONE
//                }
            }
            R.id.btnViewEnterprise -> {
//                val intent = Intent(this, EnterpriseActivity::class.java)
//                intent.putExtra(ConstantsMain.BASE_GUID, baseGuid)
//                intent.putExtra(ConstantsMain.IS_TESTING, isTesting)
//                startActivity(intent)
            }
            R.id.btnDeleteBase -> {
                val myDialogFragment =
                    MyDialogFragment(this, "Удалить базу?", MyDialogFragment.YESNO, 1)
                myDialogFragment.show(
                    this@BaseNameSettingActivity.supportFragmentManager,
                    "dialog"
                )
            }
            R.id.chbAutoExchange -> {
                btnSaveSetting.visibility = View.VISIBLE
            }
//            R.id.cbIsTesting -> {
//                btnSaveSetting.visibility = View.VISIBLE
//            }
        }
    }

    private fun saveSetting() {
//        val baseName: BaseName = MyApplication.get().baseSelected
//        baseName.name = etBaseName.text.toString()
//        //                baseName.setGuid(etGuid.getText().toString());
//        baseName.login = etLogin.text.toString()
//        baseName.password = etPassword.text.toString()
//        baseName.apikey = etAPIKey.text.toString()
//        baseName.serviceid = etServiceID.text.toString()
//        baseName.issuerid = tvIssuerID.text.toString()
//        if (cbAutoExchange.isChecked) {
//            baseName.autoexchange = "1"
//        } else {
//            baseName.autoexchange = "0"
//        }
//        baseName.userautoexchange = etUserAutoExchange.text.toString()
//        mViewModel.insertBaseName(baseName)
//        Toast.makeText(this@BaseNameSettingActivity, "Сохранено", Toast.LENGTH_SHORT).show()
//        btnSaveSetting.visibility = View.GONE
    }

    override fun beforeTextChanged(
        s: CharSequence,
        start: Int,
        count: Int,
        after: Int
    ) {
    }

    override fun onTextChanged(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
    }

    override fun afterTextChanged(s: Editable) {
        btnSaveSetting.visibility = View.VISIBLE
    }

    override fun myOnClick(view: View?, myPosition: Int, key: String) {
        when (key) {
            ConstantsMain.IS_USERS_ADAPTER -> {
                val user: Users = usersAdapter.getUser(myPosition)
                etNewUser.setText(user.name)
                etNewUser.visibility = View.VISIBLE
                ibtnDeleteUser.visibility = View.VISIBLE
                btnSaveUser.visibility = View.GONE
            }
            ConstantsMain.IS_BASENAME_TAB_ADAPTER -> {
                when (myPosition) {
                    0 -> {
                        grpEnterprise.visibility = View.GONE
                        grpEntity.visibility = View.VISIBLE
                        grpUsers.visibility = View.GONE
                    }
                    1 -> {
                        grpEnterprise.visibility = View.VISIBLE
                        grpEntity.visibility = View.GONE
                        grpUsers.visibility = View.GONE
                    }
                    2 -> {
                        grpEnterprise.visibility = View.GONE
                        grpEntity.visibility = View.GONE
                        grpUsers.visibility = View.VISIBLE
                    }
                }
            }
        }

    }

    override fun dialogFragmentOnClickPositive(flag: Int) {
//        if (flag == 1) {
//            if (baseGuid != null && !baseGuid.trim { it <= ' ' }.isEmpty()) {
//                mViewModel.deleteBaseByName(baseGuid)
//                MyApplication.get().baseSelected = null
//                finish()
//            }
//        } else if (flag == 2) {
//            saveSetting()
//            finish()
//        }
    }

    override fun dialogFragmentOnClickNegative(flag: Int) {
        if (flag == 2) {
            finish()
        }
    }

    private fun verityChanges() {
        if (btnSaveSetting.visibility == View.VISIBLE) {
            val myDialogFragment = MyDialogFragment(
                this,
                "Настройки изменены, сохранить изменения?",
                MyDialogFragment.YESNO,
                2
            )
            myDialogFragment.show(
                this@BaseNameSettingActivity.supportFragmentManager,
                "dialog"
            )
        } else {
            finish()
        }
    }

    override fun onBackPressed() {
        verityChanges()
    }
}

