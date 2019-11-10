package io.github.aungkothet.padc.assignment11.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.aungkothet.padc.assignment11.utils.DB_NAME
import io.github.aungkothet.padc.assignment11.data.vos.PhotoVO
import io.github.aungkothet.padc.assignment11.persistance.daos.PhotoDao

@Database(entities = [PhotoVO::class], exportSchema = false, version = 3)
abstract class PhotoDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao

    companion object {
        private var instance: PhotoDatabase? = null

        fun getInstance(context: Context): PhotoDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, PhotoDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance!!
        }
    }
}