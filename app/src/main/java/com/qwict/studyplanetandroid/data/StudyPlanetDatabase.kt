package com.qwict.studyplanetandroid.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [PlanetEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class StudyPlanetDatabase : RoomDatabase() {

    abstract fun planetDao(): PlanetDao

    companion object {
        @Volatile
        private var Instance: StudyPlanetDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): StudyPlanetDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    StudyPlanetDatabase::class.java,
                    "study_planet_database",
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Use a coroutine to insert data
                        val planetDao = Instance?.planetDao()
                        scope.launch {
                            planetDao?.insertAll(populateData())
                        }
                    }
                })
//                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
