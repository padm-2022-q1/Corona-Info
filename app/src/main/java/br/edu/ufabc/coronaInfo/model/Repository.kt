package br.edu.ufabc.coronaInfo.model

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
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

    private data class ServiceResult(
        val stateResult: StateEntity
    )

    private interface StatisticsService {
        @Headers(
            "Authorization: $token"
        )
        @GET("dataset/covid19/caso/data")
        suspend fun getStateStatistics(
            @Query("is_last") isLast: String,
            @Query("state") state: String): ResponseBody
    }

    private val service = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(StatisticsService::class.java)

//    private fun deserializeErrorResult(response: Response<ServiceResult>) =
//        Gson().fromJson<ServiceResult>(
//            response.errorBody()?.charStream()?.readText(),
//            object : TypeToken<ServiceResult>() {}.type
//        )

    private fun checkResponseCodes(response: Response<ServiceResult>) = when (response.code()) {
        200 -> {}
        401 -> throw Exception("User id is not authorized")
        500 -> throw Exception("Internal server error")
        406 -> throw Exception("Invalid input data format")
        else -> throw Exception("Invalid response code")
    }

    /**
     * Retrieves all information from a State.
     * @return a list of informations
     */
    suspend fun getStateInfo(state: String): ResponseBody = withContext(Dispatchers.IO) {
        service.getStateStatistics(isLast, state).let { response ->
//            Log.i("BODY", response.body().toString())
//            Log.i("BODY", response.message())
//            Log.i("BODY", response.errorBody().toString())
//            Log.i("BODY", response.raw().toString())
//            Log.i("BODY", response.code().toString())
            //checkResponseCodes(response)
            //response.body()?.stateResult ?: throw Exception("Failed to get body message")
            response ?: throw Exception("Failed to get body message")
        }
    }
}
