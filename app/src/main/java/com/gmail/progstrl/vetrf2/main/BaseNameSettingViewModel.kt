package com.gmail.progstrl.vetrf2.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gmail.progstrl.vetrf2.database.BaseDao
import com.gmail.progstrl.vetrf2.database.BaseName
import com.gmail.progstrl.vetrf2.database.DatabaseVetrf
import com.gmail.progstrl.vetrf2.database.Users


class BaseNameSettingViewModel(
    application: Application
) : AndroidViewModel(application) {
    var isAlive = false
    var repository: BaseNameSettingRepository
//    val baseNameMLD: MutableLiveData<BaseName>
//    val allUsers: LiveData<List<Users>>

    init {
        val baseDao = DatabaseVetrf.getDatabase(application, viewModelScope).baseDao()
        repository = BaseNameSettingRepository(baseDao)
//        baseNameMLD = repository.baseNameMLD
//        allUsers = repository.allUsers
    }

    fun getBaseNameById(baseGuid: String): LiveData<List<BaseName>> {
        return repository.getBaseNameById(baseGuid)
    }

//    fun insertBaseName(baseName: BaseName?) {
//        repository.insertBaseName(baseName)
//    }
//
//    fun insertUser(user: Users?) {
//        repository.insertUser(user)
//    }

//    fun deleteUserByName(
//        userName: String?,
//        baseGuid: String?,
//        isTesting: String?
//    ) {
//        repository.deleteUserByName(userName, baseGuid, isTesting)
//    }
//
//    fun deleteBaseByName(baseGuid: String?) {
//        repository.deleteBaseByName(baseGuid)
//    }


}