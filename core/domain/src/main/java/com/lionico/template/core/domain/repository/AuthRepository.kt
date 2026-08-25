package com.lionico.template.core.domain.repository

import com.lionico.template.core.model.UserData
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val userData: Flow<UserData?>
    suspend fun signInWithGoogle(idToken: String): Result<Unit>
    suspend fun signOut()
}
