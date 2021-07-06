package com.gmail.progstrl.vetrf2.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBaseName(baseName: BaseName): Long

    @Update
    suspend fun updateBaseName(baseName: BaseName)

    suspend fun insertBaseNameOnConflict(baseName: BaseName){
        if(insertBaseName(baseName) == -1L){
            updateBaseName(baseName)
        }
    }

    @Query("SELECT * FROM basename")
    fun getAllBaseName(): LiveData<List<BaseName>>

    @Query("SELECT * FROM basename WHERE id = :baseGuid")
    fun getBaseNameById(baseGuid: String): LiveData<List<BaseName>>

}