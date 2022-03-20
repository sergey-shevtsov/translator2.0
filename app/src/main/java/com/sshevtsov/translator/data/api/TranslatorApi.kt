package com.sshevtsov.translator.data.api

import android.util.Log
import com.sshevtsov.translator.data.api.entity.DataModelResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"
private const val SEARCH = "words/search"

interface TranslatorApi {

  @GET(SEARCH)
  suspend fun search(@Query("search") wordToSearch: String): Array<DataModelResponse>

  companion object {

    fun create(): TranslatorApi =
      Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(createOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TranslatorApi::class.java)

    private fun createOkHttpClient(): OkHttpClient =
      OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private val httpLoggingInterceptor: HttpLoggingInterceptor
      get() = HttpLoggingInterceptor { message ->
        Log.i("Network", message)
      }.apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
      }
  }
}