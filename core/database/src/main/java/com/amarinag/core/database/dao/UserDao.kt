package com.amarinag.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amarinag.core.database.model.UserEntity

@Dao
interface UserDao {
    @Query(value = "SELECT * FROM users")
    fun getUsers(): PagingSource<Int, UserEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Query("DELETE FROM users")
    suspend fun clearUsers()
}