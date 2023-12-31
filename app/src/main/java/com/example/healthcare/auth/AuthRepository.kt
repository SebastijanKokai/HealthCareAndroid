package com.example.healthcare.auth

import android.content.Intent
import android.content.IntentSender
import com.example.healthcare.data.room.entities.UserEntity
import com.example.healthcare.models.login.LoginRequest
import com.example.healthcare.models.login.LoginResult
import com.example.healthcare.models.register.RegisterRequest
import com.example.healthcare.models.register.RegisterResult
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val oneTapClient: SignInClient,
    private val signInRequest: BeginSignInRequest,
    private val firebaseAuth: FirebaseAuth
) : IAuthRepository {

    override suspend fun registerUser(registerRequest: RegisterRequest): RegisterResult {
        return try {
            val user = firebaseAuth.createUserWithEmailAndPassword(
                registerRequest.email,
                registerRequest.password
            ).await().user
            RegisterResult(
                data = user?.run {
                    UserEntity(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString(),
                        contactNumber = phoneNumber,
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            RegisterResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    override suspend fun login(loginRequest: LoginRequest): LoginResult {
        return try {
            val user =
                firebaseAuth.signInWithEmailAndPassword(loginRequest.email, loginRequest.password)
                    .await().user
            LoginResult(
                data = user?.run {
                    UserEntity(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString(),
                        contactNumber = phoneNumber,
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            LoginResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    override suspend fun loginWithGoogle(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(signInRequest).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    override suspend fun loginWithIntent(intent: Intent): LoginResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)

        return try {
            val user = firebaseAuth.signInWithCredential(googleCredentials).await().user
            LoginResult(
                data = user?.run {
                    UserEntity(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString(),
                        contactNumber = phoneNumber,
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            LoginResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    override suspend fun logout() {
        try {
            oneTapClient.signOut().await()
            firebaseAuth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    override fun getLoggedInUser(): UserEntity? = firebaseAuth.currentUser?.run {
        UserEntity(
            userId = uid,
            username = displayName,
            profilePictureUrl = photoUrl?.toString(),
            contactNumber = phoneNumber,
            email = email
        )
    }

    override fun getFirebaseAuthState() = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        firebaseAuth.addAuthStateListener(authStateListener)
        awaitClose {
            firebaseAuth.removeAuthStateListener(authStateListener)
        }
    }

}