package com.amarinag.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amarinag.core.database.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query(value = "SELECT * FROM users")
    fun getUsers(): PagingSource<Int, UserEntity>

    @Query(
        """
        SELECT * FROM users 
        WHERE LOWER(email) LIKE '%' || LOWER(:query) || '%' 
           OR LOWER(phone) LIKE '%' || LOWER(:query) || '%'
           OR LOWER(cell) LIKE '%' || LOWER(:query) || '%'
           OR LOWER(usernameFirst) LIKE '%' || LOWER(:query) || '%'
           OR LOWER(usernameLast) LIKE '%' || LOWER(:query) || '%'
    """
    )
    fun getUserFiltered(query: String): PagingSource<Int, UserEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Query("DELETE FROM users")
    suspend fun clearUsers()

    @Query("SELECT * FROM users WHERE email == :email LIMIT 1")
    fun getUserByEmail(email: String): Flow<UserEntity>

    @Query("DELETE FROM users WHERE email == :email")
    suspend fun deleteUserByEmail(email: String)
}