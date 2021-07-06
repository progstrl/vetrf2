package com.gmail.progstrl.vetrf2.main

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmail.progstrl.vetrf2.database.*

class BaseNameSettingRepository(
    private val baseDao: BaseDao
) {




    fun getBaseNameById(baseGuid: String):LiveData<List<BaseName>>{
       return baseDao.getBaseNameById(baseGuid)
    }

//    fun getBaseNameById(baseGuid: String?) {
//        val task = GetBaseNameByIdAsynkTask(baseDao)
//        task.delegate = this
//        task.execute(baseGuid)
//    }

//    fun insertBaseName(baseName: BaseName?) {
//        val task = InsertAsynkTask(baseDao)
//        task.execute(baseName)
//    }
//
//    fun insertUser(user: Users?) {
//        val task = InsertUserAsynkTask(usersDao)
//        task.execute(user)
//    }

    //    public void getAllUsersByIdBase(String baseNameId) {
    //        allUsers = usersDao.getAllUsers(baseNameId);
    //    }
//    fun deleteUserByName(
//        userName: String?,
//        baseGuid: String?,
//        isTesting: String?
//    ) {
//        val task =
//            DeleteUserByNameAsynkTask(usersDao, baseGuid, isTesting)
//        task.execute(userName)
//    }

//    fun deleteBaseByName(baseGuid: String?) {
//        val task = DeleteBaseByNameAsynkTask(baseDao)
//        task.execute(baseGuid)
//    }

//    private class DeleteBaseByNameAsynkTask(private val baseDao: BaseDao) :
//        AsyncTask<String?, Void?, Void?>() {
//        protected override fun doInBackground(vararg strings: String): Void? {
//            baseDao.deleteBaseByName(strings[0])
//            return null
//        }
//
//    }

//    private class DeleteUserByNameAsynkTask(
//        private val userDao: UsersDao,
//        private val baseGuid: String?,
//        private val isTesting: String?
//    ) : AsyncTask<String?, Void?, Void?>() {
//        protected override fun doInBackground(vararg strings: String): Void? {
//            userDao.deleteUserByName(strings[0], baseGuid, isTesting)
//            return null
//        }
//
//    }
//
//    private class InsertUserAsynkTask(private val userDao: UsersDao) :
//        AsyncTask<Users?, Void?, Void?>() {
//        protected override fun doInBackground(vararg users: Users): Void? {
//            userDao.insertUser(users[0])
//            return null
//        }
//
//    }
//
//    private class InsertAsynkTask(var baseDao: BaseDao) :
//        AsyncTask<BaseName?, Void?, Void?>() {
//        protected override fun doInBackground(vararg baseNames: BaseName): Void? {
//            baseDao.insertBaseNameOnConflict(baseNames[0])
//            return null
//        }
//
//    }

//    private class GetBaseNameByIdAsynkTask(private val baseDao: BaseDao) :
//        AsyncTask<String?, Void?, BaseName?>() {
//        var delegate: BaseNameSettingRepository? = null
//        protected override fun doInBackground(vararg strings: String): BaseName? {
//            return baseDao.getBaseNameById(strings[0])
//        }
//
//        override fun onPostExecute(baseName: BaseName?) {
//            delegate!!.setBaseNameById(baseName)
//        }
//
//    }

//    private fun setBaseNameById(baseName: BaseName?) {
//        baseNameMLD.value = baseName
//    }


}