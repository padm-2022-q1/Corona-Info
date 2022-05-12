package br.edu.ufabc.coronaInfo.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


/**
 * The repository.
 */
class Repository {
    companion object {
        private const val url = "http://api.brasil.io/dataset/covid19/caso/data?"
        private const val token = "Token e70426895cb3e589fa75d43c597515910815b736"
    }

    private data class ServiceResult(
        val count: Long?,
        val next: String?,
        val previous: String?,
        val infos: List<StateInfo>?
    )

    private interface ContactService {

        @Headers(
            "Authorization: $token"
        )
        @GET("?is_last=True&state={state}")
        suspend fun list(@Path("state") state: String): Response<ServiceResult>

    }

    private val service = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ContactService::class.java)

    private fun deserializeErrorResult(response: Response<ServiceResult>) =
        Gson().fromJson<ServiceResult>(
            response.errorBody()?.charStream()?.readText(),
            object : TypeToken<ServiceResult>() {}.type
        )

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
    suspend fun getInfoState(state: String): List<StateInfo> = withContext(Dispatchers.IO) {
        service.list(state).let { response ->
            checkResponseCodes(response)
            response.body()?.infos ?: throw Exception("Failed to retrieve contact list")
        }
    }


}
