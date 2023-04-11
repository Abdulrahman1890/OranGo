package com.example.data.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.roomDB.entities.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class OranGoDataBase : RoomDatabase() {
    abstract val orangoDao: Dao

    companion object {
        @Volatile
        private var INSTANCE: OranGoDataBase? = null

        fun getInstance(context: Context): OranGoDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OranGoDataBase::class.java,
                    "oranGo"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                instance
            }
        }
    }
}