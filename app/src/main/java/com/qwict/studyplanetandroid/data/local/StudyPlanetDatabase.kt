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

/**
 * Room Database for the Study Planet application, managing entities such as [PlanetRoomEntity] and [UserRoomEntity].
 *
 * @property planetDao The Data Access Object (DAO) for interacting with the "planets" table.
 * @property userDao The Data Access Object (DAO) for interacting with the "users" table.
 */
@Database(
    entities = [PlanetRoomEntity::class, UserRoomEntity::class],
    version = 4,
    exportSchema = false,
)
abstract class StudyPlanetDatabase : RoomDatabase() {

    abstract fun planetDao(): PlanetDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: StudyPlanetDatabase? = null

        /**
         * Gets an instance of the [StudyPlanetDatabase].
         *
         * @param context The application context.
         * @param scope The coroutine scope for database operations.
         * @return The [StudyPlanetDatabase] instance.
         */
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
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
