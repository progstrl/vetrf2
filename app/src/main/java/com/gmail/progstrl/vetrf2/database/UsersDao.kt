package com.gmail.progstrl.vetrf2.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
abstract class UsersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertUser(user: Users?): Long

    @Update
    abstract suspend fun updateUser(user: Users?)


    suspend fun insertUserOnConflict(user: Users?) {
        if (insertUser(user) == -1L) {
            updateUser(user)
        }
    }

//    @Query("SELECT * FROM users WHERE businessentityguid = :guid")
//    abstract fun getAllUsers(
//        guid: String?
//    ): LiveData<List<Users?>?>?
//
//    @Query("DELETE FROM users WHERE id = :userName and businessentityguid = :guid")
//    abstract suspend fun deleteUserByName(
//        userName: String?,
//        guid: String?
//    )
//
//    @Query("SELECT * FROM users WHERE businessentityguid = :guid")
//    abstract suspend fun getAllUsersByIdBase(
//        guid: String?,
//        isTesting: String?
//    ): List<Users?>?
}