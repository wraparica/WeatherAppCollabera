package wraparica.com.data.weather.repository

import wraparica.com.data.weather.UserRepository
import wraparica.com.data.weather.dao.UserDao
import wraparica.com.data.weather.entity.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineUserRepository @Inject constructor(
    private val userDao: UserDao
): UserRepository {
    override suspend fun insert(userEntity: UserEntity): Long = userDao.insert(userEntity)
    override suspend fun update(userEntity: UserEntity) = userDao.update(userEntity)
    override suspend fun logout() = userDao.logout()
    override suspend fun getCurrentLoginUser(): UserEntity? = userDao.getCurrentLoginUser()
    override suspend fun checkUser(username: String, password: String): UserEntity? = userDao.checkUser(username, password)
}