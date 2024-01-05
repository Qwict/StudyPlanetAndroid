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
import kotlinx.coroutines.CoroutineScope

/**
 * Room Database for the Study Planet application, managing entities such as [PlanetRoomEntity] and [UserRoomEntity].
 *
 * @property planetDao The Data Access Object (DAO) for interacting with the "planets" table.
 * @property userDao The Data Access Object (DAO) for interacting with the "users" table.
 */
@Database(
    entities = [PlanetRoomEntity::class, UserRoomEntity::class],
    version = 5,
    exportSchema = false,
)
abstract class StudyPlanetDatabase : RoomDatabase() {
    abstract fun planetDao(): PlanetDao

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: StudyPlanetDatabase? = null

        /**
         * Gets an instance of the [StudyPlanetDatabase].
         *
         * @param context The application context.
         * @param scope The coroutine scope for database operations. Only used for populating the database.
         * @return The [StudyPlanetDatabase] instance.
         */
        fun getDatabase(
            context: Context,
            scope: CoroutineScope,
        ): StudyPlanetDatabase {
            Log.d("StudyPlanetDatabase", "Creating database")
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    StudyPlanetDatabase::class.java,
                    "study_planet_database",
                ).addCallback(
                    object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Use a coroutine to insert data
//                            val planetDao = instance?.planetDao()
//                            val userDao = instance?.userDao()
//                        Log.i("StudyPlanetDatabase", "Inserting all planets and users")
//                        scope.launch {
//                            planetDao?.insertAll(populatePlanets())
//                        }
                        }
                    },
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}
