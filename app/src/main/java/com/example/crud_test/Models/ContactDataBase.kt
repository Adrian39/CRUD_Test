package com.example.crud_test.Models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crud_test.Controllers.ContactosDao
import com.example.crud_test.Data.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactDataBase : RoomDatabase() {
    abstract fun contactos(): ContactosDao

    companion object{
        @Volatile
        private  var INSTANCE : ContactDataBase? = null

        fun getDatabase(context: Context) : ContactDataBase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDataBase::class.java,
                    "contact_database"
                ).build()

                INSTANCE = instance

                return instance
            }
        }
    }

}