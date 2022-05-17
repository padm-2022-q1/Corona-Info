package br.edu.ufabc.coronaInfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import br.edu.ufabc.coronaInfo.model.Repository
import br.edu.ufabc.coronaInfo.model.StateEntity
import com.google.gson.Gson

/**
 * Main ViewModel.
 */
class MainViewModel : ViewModel() {
    private val repository = Repository()

    /**
     * LiveData to signal an ongoing data loading operation.
     */
    val isLoading = MutableLiveData(false)

    /**
     * Class to signal ViewModel status.
     */
    sealed class Status {
        /**
         * Error status.
         * @property e a throwable
         */
        class Error(val e: Exception) : Status()

        /**
         * Success status.
         */
        object Success : Status()
    }

    /**
     * A result that holds a contact list.
     * @property result the result, is StateEntity.StateInfo
     * @property status the status
     */
    data class StateStatisticsResult(
        val result: StateEntity.StateInfo?,
        val status: Status
    )

    fun getStateStatistics(state: String) = liveData {
        try {
            val response = repository.getStateInfo(state)
            val gson = Gson()
            val states = gson.fromJson(response.string(), StateEntity.StateInfo::class.java)

            emit(StateStatisticsResult(states, Status.Success))
        } catch (e: retrofit2.HttpException) {
            emit(
                StateStatisticsResult(
                    null,
                    Status.Error(Exception("Failed to call state info API", e))
                )
            )
        }
    }
}
