package com.lionico.template.core.firebase

import com.google.firebase.auth.FirebaseAuth
import com.lionico.template.core.domain.repository.AuthRepository
import com.lionico.template.core.model.UserData
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseAuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : AuthRepository {
    override val userData: Flow<UserData?> = callbackFlow {
        val authListener = FirebaseAuth.AuthStateListener { auth ->
            val user = auth.currentUser
            val userData = user?.let {
                UserData(
                    id = it.uid,
                    email = it.email ?: "",
                    name = it.displayName,
                    photoUrl = it.photoUrl?.toString(),
                )
            }
            trySend(userData)
        }
        firebaseAuth.addAuthStateListener(authListener)
        awaitClose {
            firebaseAuth.removeAuthStateListener(authListener)
        }
    }

    override suspend fun signInWithGoogle(idToken: String): Result<Unit> {
        // Implementation for Google Sign-In would go here
        return Result.success(Unit)
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }
}
