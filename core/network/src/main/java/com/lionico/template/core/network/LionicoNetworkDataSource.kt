package com.lionico.template.core.network

import com.lionico.template.core.model.UserData

/**
 * Interface representing network calls to the Lionico backend
 */
interface LionicoNetworkDataSource {
    suspend fun getUserData(userId: String): UserData
}
