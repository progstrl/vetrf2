package com.gmail.progstrl.vetrf2.main

import android.app.Application
import androidx.lifecycle.LiveData
import com.gmail.progstrl.vetrf2.database.BaseDao
import com.gmail.progstrl.vetrf2.database.BaseName

class BaseNameRepository(private val baseDao: BaseDao){

    fun getAllBaseName(): LiveData<List<BaseName>> {
        return baseDao.getAllBaseName()
    }


    suspend fun insertBaseName(baseName: BaseName){
        baseDao.insertBaseNameOnConflict(baseName)
    }


}