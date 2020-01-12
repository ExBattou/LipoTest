package net.adrote.cabbieonetest.api


import net.adrote.lipotest.model.FilmQuery
import net.adrote.lipotest.model.Personaje
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface SWApi {
    companion object {
        private const val BASE_URL = "https://swapi.co/api/"

        fun create(): SWApi {

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()


            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(SWApi::class.java)
        }
    }

    @GET("films")
    suspend fun firstLoad(): Response<FilmQuery>

    @GET("people/{personajeId}")
    suspend fun getCharacter(@Path("personajeId") personajeId:String): Response<Personaje>
}