package com.amarinag.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amarinag.core.database.model.UserEntity

@Database(
    version = 1,
    exportSchema = true,
    entities = [UserEntity::class]
)
internal abstract class RandomUserDatabase: RoomDatabase()