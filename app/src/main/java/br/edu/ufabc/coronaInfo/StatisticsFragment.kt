package br.edu.ufabc.coronaInfo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import br.edu.ufabc.coronaInfo.databinding.FragmentStatisticsBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Locale

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
        val spinner = binding.statisticsDropdownList
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) { /* Mandatory Override */ }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val text: String = spinner.selectedItem.toString()
                viewModel.getStateStatistics(text).observe(viewLifecycleOwner) { result ->
                    when (result.status) {
                        is MainViewModel.Status.Success -> {
                            bindResultEvents(result)
                        }
                        is MainViewModel.Status.Error -> {
                            Log.e("VIEW", "Failed to call API", result.status.e)
                            if (view != null) {
                                Snackbar.make(
                                    view.rootView, "Failed to fetch item",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun bindResultEvents(result: MainViewModel.StateStatisticsResult) {
        val cityList = result.result?.results
        binding.deathStatisticsNumber.text = cityList?.get(0)?.deaths.toString()
        binding.confirmedCasesStatisticsNumber.text = cityList?.get(0)?.confirmed.toString()
        binding.confirmedCases100kStatisticsNumber.text =
            String.format(Locale.getDefault(), "%.2f", cityList?.get(0)?.confirmedPer100kInhabitants)
        binding.deathRateStatisticsNumber.text = cityList?.get(0)?.deathRate.toString()
        binding.populationStatisticsNumber.text = cityList?.get(0)?.estimatedPopulation.toString()
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
