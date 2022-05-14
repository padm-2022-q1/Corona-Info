package br.edu.ufabc.coronaInfo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import br.edu.ufabc.coronaInfo.databinding.FragmentStatisticsBinding
import com.google.android.material.snackbar.Snackbar

class StatisticsFragment : Fragment() {
    private lateinit var binding: FragmentStatisticsBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        adapterSpinner()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getStateStatistics("SP").observe(viewLifecycleOwner) { result ->
            when (result.status) {
                is MainViewModel.Status.Success -> {
                    val cityList = result.result?.results
                    binding.confirmedCasesStatisticsNumber.text = cityList?.get(0)?.confirmed.toString()
                }
                is MainViewModel.Status.Error -> {
                    Log.e("VIEW", "Failed to call API", result.status.e)
                    Snackbar.make(
                        view.rootView, "Failed to fetch item",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }

        }
    }

    private fun adapterSpinner() {
        val spinner = binding.statisticsDropdownList
        activity?.let {
            ArrayAdapter.createFromResource(
                it.baseContext,
                R.array.states_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        }
    }
}
