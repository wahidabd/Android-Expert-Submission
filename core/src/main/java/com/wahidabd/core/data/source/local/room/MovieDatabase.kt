package com.wahidabd.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wahidabd.core.data.source.local.entity.MovieEntity
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase =
            instance ?: synchronized(this){
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(context: Context): MovieDatabase {
            val passphrase = SQLiteDatabase.getBytes("wahidabd".toCharArray())
            val factory = SupportFactory(passphrase)

            return Room.databaseBuilder(context, MovieDatabase::class.java, "movie.db")
                .fallbackToDestructiveMigration()
                .openHelperFactory(factory)
                .build()
        }

    }
}