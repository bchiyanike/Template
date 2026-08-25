package com.lionico.template.core.domain.repository

import com.lionico.template.core.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserData(userId: String): Flow<UserData?>
    suspend fun syncWith(userId: String): Boolean
}
