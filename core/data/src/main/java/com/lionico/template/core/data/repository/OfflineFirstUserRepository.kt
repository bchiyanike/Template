package com.lionico.template.core.data.repository

import com.lionico.template.core.database.dao.UserDao
import com.lionico.template.core.database.model.asEntity
import com.lionico.template.core.database.model.asExternalModel
import com.lionico.template.core.domain.repository.UserRepository
import com.lionico.template.core.model.UserData
import com.lionico.template.core.network.LionicoNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFirstUserRepository @Inject constructor(
    private val userDao: UserDao,
    private val network: LionicoNetworkDataSource,
) : UserRepository {

    override fun getUserData(userId: String): Flow<UserData?> =
        userDao.getUser(userId).map { it?.asExternalModel() }

    override suspend fun syncWith(userId: String): Boolean {
        return try {
            val networkUser = network.getUserData(userId)
            userDao.insertOrReplaceUser(networkUser.asEntity())
            true
        } catch (e: Exception) {
            false
        }
    }
}
