package com.qwict.studyplanetandroid.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [Planet::class, User::class],
    version = 5,
    exportSchema = false,
)
abstract class StudyPlanetDatabase : RoomDatabase() {

    abstract fun planetDao(): PlanetDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: StudyPlanetDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): StudyPlanetDatabase {
            Log.d("StudyPlanetDatabase", "Creating database")
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    StudyPlanetDatabase::class.java,
                    "study_planet_database",
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                })
//                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
