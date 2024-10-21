package wraparica.com.data.weather.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import wraparica.com.data.weather.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: UserEntity): Long

    @Update
    suspend fun update(user: UserEntity)

    @Query("SELECT * FROM user where username = :username AND password = :password")
    suspend fun checkUser(username: String, password: String): UserEntity?

    @Query("SELECT * FROM user where login = 1")
    suspend fun getCurrentLoginUser(): UserEntity?

    @Query("UPDATE user set login = 0")
    suspend fun logout()
}