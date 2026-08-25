package com.lionico.template.core.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lionico.template.core.model.UserData
import com.lionico.template.core.network.LionicoNetworkDataSource
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Retrofit API declaration for Lionico Network
 */
private interface RetrofitLionicoNetworkApi {
    @GET(value = "users/{userId}")
    suspend fun getUserData(@Path("userId") userId: String): UserData
}

/**
 * [Retrofit] backed [LionicoNetworkDataSource]
 */
@Singleton
class RetrofitLionicoNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : LionicoNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl("https://api.lionico.com/") // TODO: Use BuildConfig or inject base URL
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitLionicoNetworkApi::class.java)

    override suspend fun getUserData(userId: String): UserData =
        networkApi.getUserData(userId = userId)
}
