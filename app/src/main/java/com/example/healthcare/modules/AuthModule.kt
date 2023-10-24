package com.example.healthcare.modules

import android.content.Context
import com.example.healthcare.BuildConfig
import com.example.healthcare.auth.AuthRepository
import com.example.healthcare.auth.IAuthRepository
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {
    private val signInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(BuildConfig.SERVER_CLIENT_ID)
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .build()

    @Provides
    @Singleton
    fun provideAuthRepository(@ApplicationContext applicationContext: Context): IAuthRepository {
        return AuthRepository(
            Identity.getSignInClient(applicationContext),
            signInRequest,
            Firebase.auth
        )
    }
}