package wraparica.com.data.weather

import wraparica.com.data.weather.entity.UserEntity

interface UserRepository {
    suspend fun insert(userEntity: UserEntity): Long
    suspend fun update(userEntity: UserEntity)
    suspend fun logout()
    suspend fun checkUser(username: String, password: String): UserEntity?
    suspend fun getCurrentLoginUser(): UserEntity?
}