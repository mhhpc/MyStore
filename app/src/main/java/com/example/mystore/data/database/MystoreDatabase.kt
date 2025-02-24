package com.example.mystore.data.database

import com.example.mystore.data.database.ProductDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mystore.data.models.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class MystoreDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: MystoreDatabase? = null

        fun getDatabase(context: Context): MystoreDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MystoreDatabase::class.java,
                    "mystore_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
