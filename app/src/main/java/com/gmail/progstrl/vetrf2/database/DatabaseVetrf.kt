package com.gmail.progstrl.vetrf2.database

import android.content.Context
import android.content.res.Resources
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(version = 1, entities = [BaseName::class,Users::class], exportSchema = false)
abstract class DatabaseVetrf : RoomDatabase() {

    abstract fun baseDao(): BaseDao
    abstract fun usersDao(): UsersDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseVetrf? = null

        fun getDatabase(
            context: Context,
            coroutineScope: CoroutineScope
        ): DatabaseVetrf {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseVetrf::class.java,
                    "vetrf2_database"
                )
                    //.addCallback(DatabaseVetrfCallback(coroutineScope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

//    private class  DatabaseVetrfCallback(
//        private  val scope: CoroutineScope) :
//        Callback() {
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            INSTANCE?.let {database ->
//                scope.launch {
//                    val baseDao = database.baseDao()
//                    prePopulateDatabase(baseDao)
//                }
//            }
//        }
//
//        private suspend fun prePopulateDatabase(baseDao: BaseDao) {
//            TODO("Not yet implemented")
//        }

    //}

}