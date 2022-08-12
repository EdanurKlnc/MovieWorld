package com.example.movieworld.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val base_url = "https://api.themoviedb.org/3/"
const val Poster_base_url = "https://image.tmdb.org/t/p/w342"

object NetworkSingleton { //Singleton bir class’ın dışarıdan birden fazla instance’ını oluşturmayı engeller.
    val service by lazy { //değişkene ilk kez ulaşıldığında yaratılmasını sağlar
        createApiService() //servise ilk ulaşıldığında yapılacak
    }

    private fun createApiService(): TmdbService {
        val okHttpClientBuilder = OkHttpClient.Builder().addNetworkInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        okHttpClientBuilder.addInterceptor(AuthenticationInterceptor())
        val okHttpClient = okHttpClientBuilder.build()

        return Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
            .create(TmdbService::class.java)

    }

}