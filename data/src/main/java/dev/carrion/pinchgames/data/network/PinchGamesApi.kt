package dev.carrion.pinchgames.data.network

import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface PinchGamesApi {

    @Headers("user-key: $API_KEY")
    @POST("/games")
    fun getGames(@Body requestBody: RequestBody): Call<List<NetworkDataEntity.Game>>

    @Headers("user-key: $API_KEY")
    @GET("/games")
    fun getGamesLegacy(@Query("fields") fields: String, @Query("limit") limit: Int, @Query("offset") offset: Int): Call<List<NetworkDataEntity.Game>>

    companion object {
        private const val BASE_URL_LEGACY = "https://api-endpoint.igdb.com"
        private const val BASE_URL = "https://api-v3.igdb.com"

        private const val API_KEY = "ee51669f9c39f036bc7b774db7298e73"

        // Returns instance of PinchGamesApi
        fun create(): PinchGamesApi {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .connectTimeout(90L, TimeUnit.SECONDS)
                .readTimeout(90L, TimeUnit.SECONDS)
                .writeTimeout(90L, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder().baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PinchGamesApi::class.java)
        }
    }
}