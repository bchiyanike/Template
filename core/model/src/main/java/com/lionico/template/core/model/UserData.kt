package com.lionico.template.core.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id: String,
    val email: String,
    val name: String?,
    val photoUrl: String?,
    val isPremium: Boolean = false,
)
