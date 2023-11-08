package com.qwict.studyplanetandroid.data.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.qwict.studyplanetandroid.data.local.dao.PlanetDao
import com.qwict.studyplanetandroid.data.local.dao.UserDao
import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity
import com.qwict.studyplanetandroid.data.local.schema.UserRoomEntity
import com.qwict.studyplanetandroid.data.local.schema.populatePlanets
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [PlanetRoomEntity::class, UserRoomEntity::class],
    version = 1,
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
                        // Use a coroutine to insert data
                        val planetDao = Instance?.planetDao()
                        val userDao = Instance?.userDao()
                        Log.i("StudyPlanetDatabase", "Inserting all planets and users")
                        scope.launch {
                            planetDao?.insertAll(populatePlanets())
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
