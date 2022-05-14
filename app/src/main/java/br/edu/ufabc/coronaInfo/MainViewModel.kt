package br.edu.ufabc.coronaInfo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import br.edu.ufabc.coronaInfo.model.Repository
import br.edu.ufabc.coronaInfo.model.StateEntity
import com.google.gson.Gson
import okhttp3.ResponseBody

/**
 * Main ViewModel.
 */
class MainViewModel : ViewModel() {
    private val repository = Repository()

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
     * @property result the result, it any
     * @property status the status
     */
    data class StateStatisticsResult(
        val result: StateEntity.StateInfo?,
        val status: Status
    )

    fun getStateStatistics(state: String) = liveData {
        try {
            var response = repository.getStateInfo(state)
            var gson = Gson()
            var states = gson.fromJson(response.string(), StateEntity.StateInfo::class.java)
            Log.i("BODY", states.toString())

            emit(StateStatisticsResult(states, Status.Success))

        } catch (e: Exception) {
            emit(
                StateStatisticsResult(
                    null,
                    Status.Error(Exception("Failed to call state info API", e))
                )
            )
        }
    }
}
