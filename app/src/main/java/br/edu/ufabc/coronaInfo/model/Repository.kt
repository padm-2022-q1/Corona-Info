package br.edu.ufabc.coronaInfo.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * The repository.
 */
class Repository {
    companion object {
        private const val url = "https://api.brasil.io/"
        private const val token = "Token e70426895cb3e589fa75d43c597515910815b736"
        private const val isLast = "True"
    }

    private interface StatisticsService {
        @Headers(
            "Authorization: $token"
        )
        @GET("dataset/covid19/caso/data")
        suspend fun getStateStatistics(
            @Query("is_last") isLast: String,
            @Query("state") state: String
        ): ResponseBody
    }

    private val service = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(StatisticsService::class.java)

    /**
     * Retrieves all information from a State.
     * @return a response body
     */
    suspend fun getStateInfo(state: String): ResponseBody = withContext(Dispatchers.IO) {
        service.getStateStatistics(isLast, state).let { response ->
            response
        }
    }
}
